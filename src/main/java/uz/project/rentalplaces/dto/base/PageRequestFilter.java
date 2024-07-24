package uz.project.rentalplaces.dto.base;

import lombok.Data;

@Data
public class PageRequestFilter {
    private Integer pageSize = 20;
    private Integer pageNumber = 0;
}
