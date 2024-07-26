package uz.project.rentalplaces.service;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.project.rentalplaces.dto.base.ApiResponse;
import uz.project.rentalplaces.dto.place.RentalPlaceCreateDto;
import uz.project.rentalplaces.dto.place.RentalPlaceResponseDto;
import uz.project.rentalplaces.dto.sms.SmsModel;
import uz.project.rentalplaces.dto.sms.SmsResponse;
import uz.project.rentalplaces.dto.sms.SmsTokenDto;
import uz.project.rentalplaces.dto.user.UserVerifyRequestDto;
import uz.project.rentalplaces.entity.*;
import uz.project.rentalplaces.exception.RecordNotFoundException;
import uz.project.rentalplaces.exception.SmsException;
import uz.project.rentalplaces.properties.SmsServiceProperties;
import uz.project.rentalplaces.repository.ActivePlaceRepository;
import uz.project.rentalplaces.repository.RentalPlaceRepository;
import uz.project.rentalplaces.repository.SmsRepository;
import uz.project.rentalplaces.repository.SmsServiceTokenRepository;
import uz.project.rentalplaces.utils.AppUtils;

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

    public ApiResponse activePlace(){

        return new ApiResponse(SUCCESSFULLY, HttpStatus.OK.value());
    }

}
