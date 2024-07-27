package uz.project.rentalplaces.dto.place;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import uz.project.rentalplaces.entity.ActivePlaceEntity;
import uz.project.rentalplaces.entity.Attachment;
import uz.project.rentalplaces.entity.RentalPlaceEntity;
import uz.project.rentalplaces.entity.UserEntity;
import uz.project.rentalplaces.enums.RentForEnum;
import uz.project.rentalplaces.enums.RentalPlaceTypeEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivePlaceListDto {

    private UUID activeId;

    private String price;

    private LocalDate activeDay;

    private RentalPlaceTypeEnum rentalPlaceTypeEnum;

    private RentForEnum rentForEnum;

    private String info;

    private Long ownerId;

    private String ownerName;

    private String ownerPhone;

    private List<String> photos;

    private Integer longitude;

    private Integer latitude;

    public static ActivePlaceListDto toDto(ActivePlaceEntity activePlace ,RentalPlaceEntity rentalPlace , UserEntity userEntity){
        return ActivePlaceListDto.builder()
                .activeId(activePlace.getId())
                .price(activePlace.getPrice())
                .activeDay(activePlace.getDay())
                .rentalPlaceTypeEnum(rentalPlace.getRentalPlaceTypeEnum())
                .rentForEnum(rentalPlace.getRentForEnum())
                .info(rentalPlace.getInfo())
                .latitude(rentalPlace.getLatitude())
                .longitude(rentalPlace.getLongitude())
                .ownerId(userEntity.getId())
                .ownerName(userEntity.getName())
                .ownerPhone(userEntity.getPhone())
                .build();
    }
}
