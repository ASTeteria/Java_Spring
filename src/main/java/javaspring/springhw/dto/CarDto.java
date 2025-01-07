package javaspring.springhw.dto;

import lombok.Builder;

@Builder
public record CarDto(
        Long id,
        String model,
        Integer enginePower,
        Integer torque
) {}
