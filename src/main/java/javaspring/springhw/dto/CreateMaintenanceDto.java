package javaspring.springhw.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateMaintenanceDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        String description,
        @Min(value = 0, message = "Price must be greater than or equal to 0")
        Double price
) {}