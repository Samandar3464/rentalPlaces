package uz.project.rentalplaces.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.RentalPlaceEntity;

import java.util.List;
import java.util.Optional;


public interface RentalPlaceRepository extends JpaRepository<RentalPlaceEntity, Long> {

    List<RentalPlaceEntity> findAllByOwnerId(Long ownerId);

    Optional<RentalPlaceEntity> findByIdAndOwnerId(Long id, Long owner_id);

}
