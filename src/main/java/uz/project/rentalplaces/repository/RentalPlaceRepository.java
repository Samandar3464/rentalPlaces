package uz.project.rentalplaces.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.RentalPlaceEntity;

import java.util.List;



public interface RentalPlaceRepository extends JpaRepository<RentalPlaceEntity, Long> {

    List<RentalPlaceEntity> findAllByOwnerId(Long ownerId);

}
