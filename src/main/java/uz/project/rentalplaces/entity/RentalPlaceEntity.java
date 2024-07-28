package uz.project.rentalplaces.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.project.rentalplaces.dto.place.RentalPlaceCreateDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rental_place")
public class RentalPlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "place_type")
    private String rentalPlaceType;

    @Column(name = "rent_for")
    private String rentFor;

    @Column(name = "info", columnDefinition = "text")
    private String info;

    @ManyToOne
    private UserEntity owner;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attachment> photos;

    private Integer longitude;

    private Integer latitude;

    private Boolean active;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @Column(name = "created_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    public static RentalPlaceEntity toEntity(RentalPlaceCreateDto dto) {
        return RentalPlaceEntity.builder()
                .rentalPlaceType(dto.getRentalPlaceTypeEnum().getText())
                .rentFor(dto.getRentForEnum().getText())
                .info(dto.getInfo())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .createdAt(LocalDateTime.now())
                .active(false)
                .build();
    }
}
