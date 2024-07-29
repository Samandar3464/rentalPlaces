package uz.project.rentalplaces.dto.language;

import lombok.Data;
import uz.project.rentalplaces.enums.LanguageEnum;

import java.util.HashMap;

@Data
public class CreateTranslateTextDto {

    private Integer id;
    private HashMap<LanguageEnum, String> translations;
}
