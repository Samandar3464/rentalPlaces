package uz.project.rentalplaces.dto.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.project.rentalplaces.entity.UserEntity;
import uz.project.rentalplaces.entity.UserRole;
import uz.project.rentalplaces.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMe implements Serializable {

    private Long id;

    private String phone;

    private String name;

    private String surname;

    private String fatherName;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    private String firebaseToken;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String avatarUrl;

    private List<UserRole> roles;

    public static GetMe toDto(UserEntity entity) {
        return GetMe.builder()
                .id(entity.getId())
                .phone(entity.getPhone())
                .name(entity.getName())
                .surname(entity.getSurname())
                .fatherName(entity.getFatherName())
                .birthDate(entity.getBirthDate())
                .createdAt(entity.getCreatedAt())
                .firebaseToken(entity.getFirebaseToken())
                .gender(entity.getGender())
                .roles(entity.getRole())
                .build();
    }

}
