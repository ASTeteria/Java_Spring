package javaspring.springhw.mapper;

import javaspring.springhw.dto.CarDto;
import javaspring.springhw.dto.CreateCarDto;
import javaspring.springhw.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarDto toDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .enginePower(car.getEnginePower())
                .torque(car.getTorque())
                .build();
    }

    public Car toEntity(CreateCarDto createCarDto) {
        Car car = new Car();
        car.setModel(createCarDto.model());
        car.setEnginePower(createCarDto.enginePower());
        car.setTorque(createCarDto.torque());
        return car;
    }
}