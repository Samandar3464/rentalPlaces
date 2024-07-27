package uz.project.rentalplaces.dto.place;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DeActivatePlaceDto {

    private Long placeId;

    private Long ownerId;

    private List<LocalDate> days;

    private String price;

}
