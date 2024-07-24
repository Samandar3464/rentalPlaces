package uz.project.rentalplaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByName(String name);
    Boolean existsByName(String name);
    List<UserRole> findAllByName(String name);

}
