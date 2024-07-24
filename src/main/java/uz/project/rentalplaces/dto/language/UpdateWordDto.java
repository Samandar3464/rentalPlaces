package uz.project.rentalplaces.dto.language;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateWordDto {
    Integer languageps_id;
    Map<LanguageEnum, String> translations;
}
