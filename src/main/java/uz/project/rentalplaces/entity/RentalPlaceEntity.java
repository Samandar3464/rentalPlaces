package uz.project.rentalplaces.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Type;
import uz.project.rentalplaces.dto.place.RentalPlaceCreateDto;
import uz.project.rentalplaces.enums.RentForEnum;
import uz.project.rentalplaces.enums.RentalPlaceTypeEnum;

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
    @Column(name = "place_type", unique = true)
    private RentalPlaceTypeEnum rentalPlaceTypeEnum;

    @Column(name = "rent_for")
    private RentForEnum rentForEnum;

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
    private LocalDateTime createdAt;

    public static RentalPlaceEntity toEntity(RentalPlaceCreateDto dto) {
        return RentalPlaceEntity.builder()
                .rentalPlaceTypeEnum(dto.getRentalPlaceTypeEnum())
                .rentForEnum(dto.getRentForEnum())
                .info(dto.getInfo())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .createdAt(LocalDateTime.now())
                .active(false)
                .build();
    }
}
