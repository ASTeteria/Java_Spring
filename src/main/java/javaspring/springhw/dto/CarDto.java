package javaspring.springhw.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CarDto(
        Long id,
        String model,
        Integer enginePower,
        Integer torque,
        String ownerUsername,
        LocalDateTime lastMaintenanceTimestamp
) {}
