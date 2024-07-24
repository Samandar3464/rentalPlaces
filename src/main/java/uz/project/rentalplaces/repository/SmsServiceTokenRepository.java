package uz.project.rentalplaces.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.SmsServiceTokenEntity;

public interface SmsServiceTokenRepository extends JpaRepository<SmsServiceTokenEntity, Integer> {
}
