package uz.project.rentalplaces.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import uz.project.rentalplaces.dto.base.ApiResponse;
import uz.project.rentalplaces.dto.language.CreateTranslateTextDto;
import uz.project.rentalplaces.enums.LanguageEnum;
import uz.project.rentalplaces.entity.language.LanguageBaseWords;
import uz.project.rentalplaces.entity.language.LanguageSource;
import uz.project.rentalplaces.repository.LanguageRepository;
import uz.project.rentalplaces.repository.LanguageSourceRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static uz.project.rentalplaces.constants.Constants.SUCCESSFULLY;


@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageSourceRepository languageSourceRepository;
    private final JdbcTemplate jdbcTemplate;

    @Value("${languages.primaryLang}")
    String primaryLang;

    public ApiResponse createMainText(HashMap<String, String> dto) {
        String newWord = "";
        for (Map.Entry<String, String> entry : dto.entrySet()) {
            newWord = entry.getValue();
        }
        if (!languageRepository.existsByCategoryAndText("rent", newWord)) {
            LanguageBaseWords languageBaseWords = LanguageBaseWords.builder()
                    .text(newWord)
                    .category("rent")
                    .lang(primaryLang)
                    .build();
            languageRepository.save(languageBaseWords);
        }
        return new ApiResponse(SUCCESSFULLY , HttpStatus.OK.value());
    }


    public ApiResponse createTranslation(CreateTranslateTextDto dto) {
        try {
            Optional<LanguageBaseWords> byId = languageRepository.findById(dto.getId());
            if (byId.isPresent()) {
                LanguageBaseWords languageBaseWords = byId.get();
                List<LanguageSource> allByIdId = languageSourceRepository.findAllByLanguageBaseWordsId(languageBaseWords.getId());
                if (!allByIdId.isEmpty()) {
                    LanguageSource uz = allByIdId.get(0);
                    uz.setTranslation(dto.getTranslations().get(LanguageEnum.Uz) !=null ? dto.getTranslations().get(LanguageEnum.Uz)  : uz.getTranslation());
                    uz.setLanguage("Uz");

                    LanguageSource ru = allByIdId.get(1);
                    ru.setTranslation(dto.getTranslations().get(LanguageEnum.Ru) !=null ? dto.getTranslations().get(LanguageEnum.Ru)  : ru.getTranslation());
                    ru.setLanguage("Ru");

                    LanguageSource en = allByIdId.get(2);
                    en.setTranslation(dto.getTranslations().get(LanguageEnum.En) !=null ? dto.getTranslations().get(LanguageEnum.En)  : en.getTranslation());
                    en.setLanguage("En");

                    languageSourceRepository.saveAll(allByIdId);
                    return new ApiResponse(SUCCESSFULLY , HttpStatus.OK.value());
                }
                HashMap<LanguageEnum, String> translations = dto.getTranslations();

                languageSourceRepository.save(new LanguageSource(languageBaseWords, "Uz", translations.get(LanguageEnum.Uz) !=null ? translations.get(LanguageEnum.Uz) : null));
                languageSourceRepository.save(new LanguageSource(languageBaseWords, "Ru", translations.get(LanguageEnum.Ru) !=null ? translations.get(LanguageEnum.Ru) : null));
                languageSourceRepository.save(new LanguageSource(languageBaseWords, "En", translations.get(LanguageEnum.En)!=null ? translations.get(LanguageEnum.En) : null));
            }
        } catch (Exception e) {
            throw e;
        }
        return new ApiResponse(SUCCESSFULLY , HttpStatus.OK.value());
    }

    public ApiResponse getAllPaginated(int page, int size , String content) {
        Pageable pageable = PageRequest.of(page, size);
        if (content.equals("null")) {
            return  new ApiResponse(languageRepository.findAll(pageable),HttpStatus.OK.value());
        }
        return  new ApiResponse(languageRepository.findAllByTextContainingIgnoreCase(pageable, content),HttpStatus.OK.value());
    }

    public ApiResponse getAllByLanguage(LanguageEnum language) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select text, translation  from language_source  ,language_base_words where language_base_word_id = language_base_words.id and language = '" + language.getText() + "'");
        return new ApiResponse(maps,HttpStatus.OK.value());
    }

}
