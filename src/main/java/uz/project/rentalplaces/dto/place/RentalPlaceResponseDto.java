package uz.project.rentalplaces.dto.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import uz.project.rentalplaces.entity.RentalPlaceEntity;
import uz.project.rentalplaces.enums.RentForEnum;
import uz.project.rentalplaces.enums.RentalPlaceTypeEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalPlaceResponseDto {

    private RentalPlaceTypeEnum rentalPlaceTypeEnum;

    private RentForEnum rentForEnum;

    private String info;

    private List<String> photos;

    private Integer price;

    private Integer longitude;

    private Integer latitude;

    private Boolean active;

    private LocalDateTime createdAt;

    private Long ownerId;

    public static RentalPlaceResponseDto toDto(RentalPlaceEntity entity){
        return RentalPlaceResponseDto.builder()
                .rentalPlaceTypeEnum(entity.getRentalPlaceTypeEnum())
                .rentForEnum(entity.getRentForEnum())
                .info(entity.getInfo())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .createdAt(entity.getCreatedAt())
                .active(entity.getActive())
                .build();
    }
}
