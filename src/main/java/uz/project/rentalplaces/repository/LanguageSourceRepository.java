package uz.project.rentalplaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.rentalplaces.entity.language.LanguageSource;

import java.util.List;


@Repository
public interface LanguageSourceRepository extends JpaRepository<LanguageSource, Integer> {

    List<LanguageSource> findAllByLanguage(String language);

    List<LanguageSource> findAllByLanguageBaseWordsId(Integer languageBaseWordId);
}