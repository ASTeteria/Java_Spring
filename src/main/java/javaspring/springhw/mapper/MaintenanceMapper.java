package javaspring.springhw.mapper;

import javaspring.springhw.dto.CreateMaintenanceDto;
import javaspring.springhw.dto.MaintenanceDto;
import javaspring.springhw.entity.Maintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface MaintenanceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Maintenance mapToMaintenance(CreateMaintenanceDto createMaintenanceDto);

    @Mapping(target = "id", expression = "java(maintenance.getId().toHexString())")
    MaintenanceDto mapToDto(Maintenance maintenance);
}