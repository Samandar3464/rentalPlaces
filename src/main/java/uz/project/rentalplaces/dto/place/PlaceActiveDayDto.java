package uz.project.rentalplaces.dto.place;


import lombok.*;
import uz.project.rentalplaces.entity.ActivePlaceEntity;
import uz.project.rentalplaces.entity.RentalPlaceEntity;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceActiveDayDto {

    private UUID id;

    private RentalPlaceResponseDto place;

    private Boolean active;

    private String price;

    private String day;

    private String activatedDate;

    private String deactivatedDate;

    public static PlaceActiveDayDto toDto(ActivePlaceEntity entity) {
        return PlaceActiveDayDto.builder()
                .id(entity.getId())
                .place(RentalPlaceResponseDto.toDto(entity.getPlace()))
                .active(entity.getActive())
                .price(entity.getPrice())
                .day(entity.getDay().toString())
                .activatedDate(entity.getActivatedDate().toString())
                .deactivatedDate(entity.getDeactivatedDate().toString())
                .build();
    }
}
