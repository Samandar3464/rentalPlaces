package uz.project.rentalplaces.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.ActivePlaceEntity;

import java.util.List;
import java.util.UUID;


public interface ActivePlaceRepository extends JpaRepository<ActivePlaceEntity, UUID> {

}
