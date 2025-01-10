package javaspring.springhw.service;


import jakarta.transaction.Transactional;
import javaspring.springhw.dto.CarDto;
import javaspring.springhw.entity.Car;
import javaspring.springhw.entity.Owner;
import javaspring.springhw.mapper.CarMapper;
import javaspring.springhw.repository.CarRepository;
import javaspring.springhw.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;
    private final CarMapper carMapper;


    @Transactional
    public CarDto createCar(CarDto carDto) {
        Owner owner = ownerRepository.findByUsername(carDto.ownerUsername())
                .orElseThrow(() -> new IllegalArgumentException("Owner with username " + carDto.ownerUsername() + " not found"));

        Car car = carMapper.toEntity(carDto);
        car.setOwner(owner);

        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }


    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .toList();
    }


    public CarDto getCarById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Car with ID " + id + " not found"));
    }


    @Transactional
    public CarDto updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car with ID " + id + " not found"));

        Owner owner = ownerRepository.findByUsername(carDto.ownerUsername())
                .orElseThrow(() -> new IllegalArgumentException("Owner with username " + carDto.ownerUsername() + " not found"));

        car.setModel(carDto.model());
        car.setEnginePower(carDto.enginePower());
        car.setTorque(carDto.torque());
        car.setOwner(owner);

        Car updatedCar = carRepository.save(car);
        return carMapper.toDto(updatedCar);
    }


    @Transactional
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("Car with ID " + id + " not found");
        }
        carRepository.deleteById(id);
    }
}