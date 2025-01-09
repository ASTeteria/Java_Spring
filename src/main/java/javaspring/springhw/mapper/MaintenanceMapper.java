package javaspring.springhw.mapper;

import javaspring.springhw.dto.CreateMaintenanceDto;
import javaspring.springhw.dto.MaintenanceDto;
import javaspring.springhw.entity.Maintenance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {
    MaintenanceDto toDto(Maintenance maintenance);

    Maintenance toEntity(CreateMaintenanceDto createMaintenanceDto);
}