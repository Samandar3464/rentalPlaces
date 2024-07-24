package uz.project.rentalplaces.dto.language;

import lombok.Data;

@Data
public class TranslateDTO {
    String locale;
    String key;
    String translation;
}
