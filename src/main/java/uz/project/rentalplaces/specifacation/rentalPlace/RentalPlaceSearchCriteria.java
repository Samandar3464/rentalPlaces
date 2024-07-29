package uz.project.rentalplaces.specifacation.rentalPlace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import uz.project.rentalplaces.enums.RentForEnum;
import uz.project.rentalplaces.enums.RentalPlaceTypeEnum;

import java.time.LocalDate;

@Data
public class RentalPlaceSearchCriteria {
    private RentalPlaceTypeEnum rentalPlaceType;

    private RentForEnum rentFor;

    private Long ownerId;

    private Boolean active;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate day;
}
