package uz.project.rentalplaces.dto.base;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse implements Serializable {

    private String message;

    private Integer status;

    private Object data;

    public ApiResponse(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(Object data, Integer status) {
        this.status = status;
        this.data = data;
    }
}
