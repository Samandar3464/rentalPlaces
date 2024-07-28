package uz.project.rentalplaces.service;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.project.rentalplaces.dto.base.ApiResponse;
import uz.project.rentalplaces.dto.base.PageRequestFilter;
import uz.project.rentalplaces.dto.place.*;
import uz.project.rentalplaces.dto.sms.SmsModel;
import uz.project.rentalplaces.dto.sms.SmsResponse;
import uz.project.rentalplaces.dto.sms.SmsTokenDto;
import uz.project.rentalplaces.dto.user.UserVerifyRequestDto;
import uz.project.rentalplaces.entity.*;
import uz.project.rentalplaces.exception.RecordAlreadyExistException;
import uz.project.rentalplaces.exception.RecordNotFoundException;
import uz.project.rentalplaces.exception.SmsException;
import uz.project.rentalplaces.properties.SmsServiceProperties;
import uz.project.rentalplaces.repository.ActivePlaceRepository;
import uz.project.rentalplaces.repository.RentalPlaceRepository;
import uz.project.rentalplaces.repository.SmsRepository;
import uz.project.rentalplaces.repository.SmsServiceTokenRepository;
import uz.project.rentalplaces.utils.AppUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static uz.project.rentalplaces.constants.Constants.*;


@Service
@RequiredArgsConstructor
public class RentalPlaceService {

    private final RentalPlaceRepository rentalPlaceRepository;
    private final ActivePlaceRepository activePlaceRepository;
    private final AttachmentService attachmentService;
    private final UserService userService;


    public ApiResponse createPlace(RentalPlaceCreateDto dto) {
        RentalPlaceEntity entity = RentalPlaceEntity.toEntity(dto);
        List<Attachment> attachments = attachmentService.saveToSystemListFile(dto.getPhotos());
        UserEntity userEntity = userService.checkUserExistById(dto.getOwnerId());
        entity.setPhotos(attachments);
        entity.setOwner(userEntity);
        rentalPlaceRepository.save(entity);
        return new ApiResponse(SUCCESSFULLY, HttpStatus.OK.value());
    }


    public ApiResponse getOwnerPlaces(Long ownerId) {
        List<RentalPlaceEntity> list = rentalPlaceRepository.findAllByOwnerId(ownerId);
        if (list.isEmpty()) {
            throw new RecordNotFoundException("Owner place not found");
        }
        List<RentalPlaceResponseDto> responseList = new ArrayList<>();
        list.forEach(obj -> {
            RentalPlaceResponseDto dto = RentalPlaceResponseDto.toDto(obj);
            dto.setPhotos(attachmentService.getUrlList(obj.getPhotos()));
            responseList.add(dto);
        });
        return new ApiResponse(responseList, HttpStatus.OK.value());
    }

    @Transactional
    public ApiResponse activePlace(ActivatePlaceDto dto) {
        RentalPlaceEntity place = rentalPlaceRepository.findById(dto.getPlaceId()).orElseThrow(() -> new RecordNotFoundException("Place not found"));
        activePlaceRepository.findByPlaceIdAndActiveTrueAndDay(dto.getPlaceId(), dto.getDay()).ifPresent((obj) -> {
            throw new RecordAlreadyExistException("This day already activated");
        });
        ActivePlaceEntity entity = ActivePlaceEntity.toEntity(dto);
        entity.setPlace(place);
        activePlaceRepository.save(entity);
        return new ApiResponse(SUCCESSFULLY, HttpStatus.OK.value());
    }

    @Transactional
    public ApiResponse deActivePlace(DeActivatePlaceDto dto) {
        rentalPlaceRepository.findById(dto.getPlaceId()).orElseThrow(() -> new RecordNotFoundException("Place not found"));
        dto.getDays().forEach(day -> {
            ActivePlaceEntity activePlaceEntity = activePlaceRepository.findByPlaceIdAndActiveTrueAndDay(dto.getPlaceId(), day).orElseThrow(() -> new RecordNotFoundException("Place not activated this day :" + day));
            activePlaceEntity.setActive(false);
            activePlaceRepository.save(activePlaceEntity);
        });
        return new ApiResponse(SUCCESSFULLY, HttpStatus.OK.value());
    }

    public ApiResponse getAllActivePlaces(PageRequestFilter page, LocalDate day) {
        PageRequest pageRequest = PageRequest.of(page.getPageNumber(), page.getPageSize());
        Page<ActivePlaceEntity> activePlaceEntities = activePlaceRepository.findAllByActiveTrueAndDay(pageRequest, day);
        List<ActivePlaceListDto> responseList = new ArrayList<>();
        activePlaceEntities.getContent().forEach(obj -> {
            RentalPlaceEntity place = obj.getPlace();
            UserEntity owner = place.getOwner();
            List<String> urlList = attachmentService.getUrlList(place.getPhotos());
            ActivePlaceListDto dto = ActivePlaceListDto.toDto(obj, place, owner);
            dto.setPhotos(urlList);
            responseList.add(dto);
        });
        return new ApiResponse(responseList, HttpStatus.OK.value());
    }

    public ApiResponse getPlaceActiveDays(Long placeId) {
        List<ActivePlaceEntity> list = activePlaceRepository.findByPlaceIdAndActiveTrue(placeId);
        if (list.isEmpty()) {
            return new ApiResponse("No active days", HttpStatus.NOT_FOUND.value());
        }
        return new ApiResponse(list, HttpStatus.OK.value());
    }

}
