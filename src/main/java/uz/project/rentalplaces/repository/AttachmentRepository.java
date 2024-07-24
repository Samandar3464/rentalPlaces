package uz.project.rentalplaces.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.project.rentalplaces.entity.Attachment;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Optional<Attachment> findByNewName(String newName);
}
