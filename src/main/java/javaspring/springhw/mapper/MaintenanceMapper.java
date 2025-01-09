package javaspring.springhw.mapper;

//import javaspring.springhw.dto.CreateMaintenanceDto;
//import javaspring.springhw.dto.MaintenanceDto;
//import javaspring.springhw.entity.Maintenance;
//import org.mapstruct.Mapper;
//
//@Mapper(componentModel = "spring")
//public interface MaintenanceMapper {
//    MaintenanceDto toDto(Maintenance maintenance);
//
//    Maintenance toEntity(CreateMaintenanceDto createMaintenanceDto);

//import javaspring.springhw.dto.CreateMaintenanceDto;
//import javaspring.springhw.dto.MaintenanceDto;
//import javaspring.springhw.entity.Maintenance;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.time.LocalDateTime;
//
//@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
//public interface MaintenanceMapper {
//
//    @Mapping(target = "id", ignore = true) // ID генерується MongoDB, тому ігноруємо
//    @Mapping(target = "name", source = "createMaintenanceDto.name")
//    @Mapping(target = "description", source = "createMaintenanceDto.description")
//    @Mapping(target = "price", source = "createMaintenanceDto.price")
//    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())") // Додаємо поточну дату
//    Maintenance toEntity(CreateMaintenanceDto createMaintenanceDto);
//
//    @Mapping(target = "id", source = "maintenance.id")
//    MaintenanceDto toDto(Maintenance maintenance);
//}
//
//import javaspring.springhw.dto.CreateMaintenanceDto;
//import javaspring.springhw.dto.MaintenanceDto;
//import javaspring.springhw.entity.Maintenance;
//import org.bson.types.ObjectId;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.time.LocalDateTime;
//
//@Mapper(componentModel = "spring", imports = {LocalDateTime.class, ObjectId.class})
//public interface MaintenanceMapper {
//
//    @Mapping(target = "id", expression = "java(new ObjectId())")
//    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
//    Maintenance mapToMaintenance(CreateMaintenanceDto createMaintenanceDto);
//
//    @Mapping(target = "id", expression = "java(maintenance.getId().toHexString())")
//    MaintenanceDto mapToDto(Maintenance maintenance);
//}

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