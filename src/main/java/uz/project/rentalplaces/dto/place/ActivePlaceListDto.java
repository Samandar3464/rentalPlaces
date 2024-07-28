package uz.project.rentalplaces.dto.place;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.project.rentalplaces.entity.ActivePlaceEntity;
import uz.project.rentalplaces.entity.RentalPlaceEntity;
import uz.project.rentalplaces.entity.UserEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ActivePlaceListDto {

    private UUID activeId;

    private String price;

    private String activeDay;

    private String placeType;

    private String rentFor;

    private String info;

    private Long ownerId;

    private Long rentalPlaceId;

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
                .placeType(rentalPlace.getRentalPlaceType())
                .rentalPlaceId(rentalPlace.getId())
                .rentFor(rentalPlace.getRentFor())
                .info(rentalPlace.getInfo())
                .latitude(rentalPlace.getLatitude())
                .longitude(rentalPlace.getLongitude())
                .ownerId(userEntity.getId())
                .ownerName(userEntity.getName())
                .ownerPhone(userEntity.getPhone())
                .build();
    }

    public ActivePlaceListDto(@JsonProperty("active_id") UUID activeId,
                              @JsonProperty("price") String price,
                              @JsonProperty("active_day") String activeDay,
                              @JsonProperty("rental_place_type") String placeType,
                              @JsonProperty("rent_for") String rentFor,
                              @JsonProperty("info") String info,
                              @JsonProperty("owner_id") Long ownerId,
                              @JsonProperty("rental_place_id") Long rentalPlaceId,
                              @JsonProperty("owner_name") String ownerName,
                              @JsonProperty("owner_phone") String ownerPhone,
                              @JsonProperty("photos") List<String> photos,
                              @JsonProperty("longitude") Integer longitude,
                              @JsonProperty("latitude") Integer latitude) {
        this.activeId = activeId;
        this.price = price;
        this.activeDay = activeDay;
        this.placeType = placeType;
        this.rentFor = rentFor;
        this.info = info;
        this.ownerId = ownerId;
        this.rentalPlaceId = rentalPlaceId;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.photos = photos;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
