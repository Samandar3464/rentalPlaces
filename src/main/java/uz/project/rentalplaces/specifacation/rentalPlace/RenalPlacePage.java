package uz.project.rentalplaces.specifacation.rentalPlace;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class RenalPlacePage {
    private Integer pageSize = 20;
    private Integer pageNumber = 0;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private String sortBy = "active_day";
}
