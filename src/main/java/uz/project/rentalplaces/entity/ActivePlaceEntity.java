package uz.project.rentalplaces.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import uz.project.rentalplaces.dto.place.ActivatePlaceDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(name = "activated_date")
    private LocalDateTime activatedDate;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @Column(name = "deactivated_date")
    private LocalDateTime deactivatedDate;

public  static ActivePlaceEntity toEntity(ActivatePlaceDto dto){
    return ActivePlaceEntity.builder()
            .active(true)
            .price(dto.getPrice())
            .day(dto.getDay())
            .activatedDate(LocalDateTime.now())
            .build();
}
}
