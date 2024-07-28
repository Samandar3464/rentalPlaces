package uz.project.rentalplaces.dto.place;


import lombok.*;
import uz.project.rentalplaces.entity.ActivePlaceEntity;
import uz.project.rentalplaces.entity.RentalPlaceEntity;
import uz.project.rentalplaces.entity.UserEntity;

import java.time.LocalDate;
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

    private String activeDay;

    private String rentalPlaceType;

    private String rentFor;

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
                .activeDay(activePlace.getDay().toString())
                .rentalPlaceType(rentalPlace.getRentalPlaceType())
                .rentFor(rentalPlace.getRentFor())
                .info(rentalPlace.getInfo())
                .latitude(rentalPlace.getLatitude())
                .longitude(rentalPlace.getLongitude())
                .ownerId(userEntity.getId())
                .ownerName(userEntity.getName())
                .ownerPhone(userEntity.getPhone())
                .build();
    }
}
