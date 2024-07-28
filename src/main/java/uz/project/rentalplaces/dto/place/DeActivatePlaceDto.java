package uz.project.rentalplaces.dto.place;

import lombok.Data;

import java.util.List;

@Data
public class DeActivatePlaceDto {

    private Long placeId;

    private Long ownerId;

    private List<String> days;

}
