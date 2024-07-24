package uz.project.rentalplaces.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.project.rentalplaces.dto.base.ApiResponse;
import uz.project.rentalplaces.dto.language.CreateTranslateTextDto;
import uz.project.rentalplaces.service.LanguageService;

import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/language")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("/create-text")
    public ApiResponse createMainText(@RequestBody HashMap<String, String> dto) {
      return   languageService.createMainText(dto);
    }

//    @Secured({"admin","translator"})
    @PostMapping("/translate")
    public ApiResponse  createTranslation(@RequestBody CreateTranslateTextDto dto) {
      return   languageService.createTranslation(dto);
    }

//    @Secured({"admin","translator"})
    @GetMapping("/get-all-words")
    public ApiResponse  getAllPaginated(@RequestParam(name = "size", defaultValue = "10") int size,
                                                   @RequestParam(name = "pageNumber", defaultValue = "0")  int page,
                                                   @RequestParam(name = "content" , defaultValue = "null")  String  content) {
        return languageService.getAllPaginated(page, size, content);
    }
    @GetMapping("/get-all-by-language")
    public ApiResponse  getAllByLanguage(@RequestParam(name = "language", defaultValue = "en") String language) {
        return languageService.getAllByLanguage(language);
    }
}
