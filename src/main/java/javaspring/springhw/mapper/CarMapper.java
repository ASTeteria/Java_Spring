package javaspring.springhw.mapper;


import javaspring.springhw.dto.CarDto;
import javaspring.springhw.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface CarMapper {

    @Mapping(target = "ownerUsername", source = "owner.username")
    @Mapping(target = "lastMaintenanceTimestamp", expression = "java(LocalDateTime.now())")
    CarDto toDto(Car car);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "lastMaintenanceTimestamp", expression = "java(null)")
    Car toEntity(CarDto carDto);
}