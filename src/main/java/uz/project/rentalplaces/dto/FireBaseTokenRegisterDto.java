package uz.project.rentalplaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireBaseTokenRegisterDto {

    private String phone;

    private String fireBaseToken;
}
