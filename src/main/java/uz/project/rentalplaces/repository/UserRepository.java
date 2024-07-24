package uz.project.rentalplaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByPhone( String phone);

    boolean existsByPhone( String phone);

}
