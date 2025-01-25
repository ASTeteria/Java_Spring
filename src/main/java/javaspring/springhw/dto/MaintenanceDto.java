package javaspring.springhw.dto;
import lombok.Builder;

@Builder
public record MaintenanceDto(
        String id,
        String name,
        String description,
        Double price
) {}
