package javaspring.springhw.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateCarDto(
        @NotBlank(message = "Model cannot be blank")
        String model,
        @Min(value = 1, message = "Engine power must be greater than 0")
        Integer enginePower,
        Integer torque,
        @NotBlank(message = "Owner username cannot be blank")
        String ownerUsername
) {}