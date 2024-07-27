package uz.project.rentalplaces.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.project.rentalplaces.dto.place.ActivatePlaceDto;
import uz.project.rentalplaces.dto.place.RentalPlaceCreateDto;
import uz.project.rentalplaces.enums.RentForEnum;
import uz.project.rentalplaces.enums.RentalPlaceTypeEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "active_place")
public class ActivePlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    private RentalPlaceEntity place;

    private Boolean active;

    //reason string may be kelishamiz
    private String price;

    @JsonFormat(pattern = "YYYY-MM-DD")
    @Column(name = "day")
    private LocalDate day;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

public  static ActivePlaceEntity toEntity(ActivatePlaceDto dto){
    return ActivePlaceEntity.builder()
            .active(true)
            .price(dto.getPrice())
            .day(dto.getDay())
            .createdAt(LocalDateTime.now())
            .build();
}
}
