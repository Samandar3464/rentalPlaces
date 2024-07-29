package uz.project.rentalplaces.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentalPlaceTypeEnum {
    DACHA("DACHA"),
    TAPCHAN("TAPCHAN"),
    SUPA("SUPA")
    ;
    private String text;

    public String value() {
        return this.toString();
    }
}
