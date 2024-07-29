package uz.project.rentalplaces.dto.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.project.rentalplaces.entity.RentalPlaceEntity;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalPlaceResponseDto {

    private String rentalPlaceType;

    private String rentFor;

    private String info;

    private List<String> photos;

    private Integer price;

    private Integer longitude;

    private Integer latitude;

    private Boolean active;

    private String createdAt;

    private Long ownerId;

    public static RentalPlaceResponseDto toDto(RentalPlaceEntity entity){
        return RentalPlaceResponseDto.builder()
                .rentalPlaceType(entity.getRentalPlaceType())
                .rentFor(entity.getRentFor())
                .info(entity.getInfo())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .createdAt(entity.getCreatedAt().toString())
                .active(entity.getActive())
                .build();
    }
}
