package uz.project.rentalplaces.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginRequestDto {

    @NotBlank
    @Size(min = 13, max = 13)
    private String phone;

    @NotBlank
    @Size(min = 6)
    private String password;
}
