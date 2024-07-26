package uz.project.rentalplaces.dto.place;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import uz.project.rentalplaces.enums.RentForEnum;
import uz.project.rentalplaces.enums.RentalPlaceTypeEnum;
import java.util.List;

@Data
public class RentalPlaceCreateDto {

    private RentalPlaceTypeEnum rentalPlaceTypeEnum;

    private RentForEnum rentForEnum;

    private String info;

    private List<MultipartFile> photos;

    private Integer price;

    private Integer longitude;

    private Integer latitude;

    private Long ownerId;

}
