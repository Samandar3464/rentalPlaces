package uz.project.rentalplaces.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.project.rentalplaces.dto.base.ApiResponse;
import uz.project.rentalplaces.dto.language.CreateTranslateTextDto;
import uz.project.rentalplaces.enums.LanguageEnum;
import uz.project.rentalplaces.service.LanguageService;

import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/language")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("/create-text")
    @PreAuthorize("hasAnyRole('admin' ,'translator')")
    public ApiResponse createMainText(@RequestBody HashMap<String, String> dto) {
        return languageService.createMainText(dto);
    }

    @PostMapping("/translate")
    @PreAuthorize("hasAnyRole('admin' ,'translator')")
    public ApiResponse createTranslation(@RequestBody CreateTranslateTextDto dto) {
        return languageService.createTranslation(dto);
    }

    @GetMapping("/get-all-words")
    @PreAuthorize("hasAnyRole('admin' ,'translator')")
    public ApiResponse getAllPaginated(@RequestParam(name = "size", defaultValue = "10") int size,
                                       @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                       @RequestParam(name = "content", defaultValue = "null") String content) {
        return languageService.getAllPaginated(page, size, content);
    }

    @GetMapping("/get-all-by-language")
    @PreAuthorize("hasAnyRole('admin' ,'translator')")
    public ApiResponse getAllByLanguage(LanguageEnum language) {
        return languageService.getAllByLanguage(language);
    }
}
