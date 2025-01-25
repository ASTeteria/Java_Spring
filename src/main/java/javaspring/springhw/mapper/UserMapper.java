package javaspring.springhw.mapper;


import javaspring.springhw.dto.RegisterUserDto;
import javaspring.springhw.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToEntity(RegisterUserDto dto);
}
