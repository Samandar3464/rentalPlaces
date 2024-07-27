package uz.project.rentalplaces.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.project.rentalplaces.dto.FireBaseTokenRegisterDto;
import uz.project.rentalplaces.dto.base.ApiResponse;
import uz.project.rentalplaces.dto.base.PageRequestFilter;
import uz.project.rentalplaces.dto.place.ActivatePlaceDto;
import uz.project.rentalplaces.dto.place.DeActivatePlaceDto;
import uz.project.rentalplaces.dto.place.RentalPlaceCreateDto;
import uz.project.rentalplaces.dto.user.*;
import uz.project.rentalplaces.service.RentalPlaceService;
import uz.project.rentalplaces.service.UserService;

import java.time.LocalDate;


@RestController
@AllArgsConstructor
@RequestMapping("/v1/rental-place")
public class RentalPlaceController {

    private RentalPlaceService rentalPlaceService;

    @PostMapping("/create")
    public ApiResponse createPlace(@ModelAttribute RentalPlaceCreateDto dto) {
        return rentalPlaceService.createPlace(dto);
    }

    @GetMapping("/get-by-owner-id")
    public ApiResponse getOwnerPlaces(@RequestParam(name = "ownerId") Long ownerId) {
        return rentalPlaceService.getOwnerPlaces(ownerId);
    }

    @PutMapping("/activate")
    public ApiResponse activePlace(@RequestBody ActivatePlaceDto dto) {
        return rentalPlaceService.activePlace(dto);
    }

    @PutMapping("/deactivate")
    public ApiResponse deActivePlace(@RequestBody DeActivatePlaceDto dto) {
        return rentalPlaceService.deActivePlace(dto);
    }


    @GetMapping("/all-active-places")
    public ApiResponse getAllActivePlaces(PageRequestFilter page, LocalDate day) {
        return rentalPlaceService.getAllActivePlaces(page, day);
    }


    @GetMapping("/get-place-active-days")
    public ApiResponse getPlaceActiveDays(@RequestParam(name = "placeId") Long placeId) {
        return rentalPlaceService.getPlaceActiveDays(placeId);
    }
}
