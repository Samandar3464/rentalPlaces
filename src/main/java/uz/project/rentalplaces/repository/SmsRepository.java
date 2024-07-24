package uz.project.rentalplaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.SmsEntity;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<SmsEntity, Long> {

    Optional<SmsEntity> findByPhoneAndCode(String phone ,String code);

}
