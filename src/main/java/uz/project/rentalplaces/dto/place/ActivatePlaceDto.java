package uz.project.rentalplaces.dto.place;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import uz.project.rentalplaces.enums.RentForEnum;
import uz.project.rentalplaces.enums.RentalPlaceTypeEnum;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
public class ActivatePlaceDto {

    private Long placeId;

    private Long ownerId;

    private LocalDate day;

    private String price;

}
