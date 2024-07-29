package uz.project.rentalplaces.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentForEnum {
    ONLY_FAMILY("ONLY_FAMILY"),
    BOYS_MENS("BOYS_MENS"),
    GIRLS_WOMENS("GIRLS_WOMENS"),
    ALL("ALL")
    ;
    private String text;

    public String value() {
        return this.toString();
    }
}
