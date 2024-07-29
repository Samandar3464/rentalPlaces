package uz.project.rentalplaces.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageEnum {

    Uz("Uz"),
    Ru("Ru"),
    En("En"),

    ;
    private String text;

    public String value() {
        return this.toString();
    }
}
