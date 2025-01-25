package javaspring.springhw.mapper;

import javaspring.springhw.dto.OwnerDto;
import javaspring.springhw.entity.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    OwnerDto toDto(Owner owner);

    Owner toEntity(OwnerDto ownerDto);
}
