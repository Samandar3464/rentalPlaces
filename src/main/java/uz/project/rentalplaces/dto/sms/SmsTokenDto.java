package uz.project.rentalplaces.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.project.rentalplaces.entity.SmsServiceTokenEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsTokenDto {

    private String message;

    @JsonProperty("data")
    private SmsServiceTokenEntity data;

    private String token_type;
}
