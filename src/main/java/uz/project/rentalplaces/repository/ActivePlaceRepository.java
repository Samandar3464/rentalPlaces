package uz.project.rentalplaces.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.ActivePlaceEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ActivePlaceRepository extends JpaRepository<ActivePlaceEntity, UUID> {

    Optional<ActivePlaceEntity> findByPlaceIdAndActiveTrueAndDay(Long place_id, LocalDate day);

    List<ActivePlaceEntity> findByPlaceIdAndActiveTrue(Long place_id);

    Page<ActivePlaceEntity> findAllByActiveTrueAndDay(Pageable pageable, LocalDate day);
}
