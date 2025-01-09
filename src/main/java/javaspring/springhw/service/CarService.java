package javaspring.springhw.service;


import jakarta.transaction.Transactional;
import javaspring.springhw.dto.CarDto;
import javaspring.springhw.dto.CreateCarDto;
import javaspring.springhw.entity.Car;
import javaspring.springhw.entity.Owner;
import javaspring.springhw.mapper.CarMapper;
import javaspring.springhw.repository.CarRepository;
import javaspring.springhw.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final OwnerRepository ownerRepository;
    private final MailService mailService;

    public List<CarDto> getCars(Integer minEnginePower, Integer maxEnginePower) {
        List<Car> cars;

        if (minEnginePower != null && maxEnginePower != null) {
            cars = carRepository.findAllByEnginePowerBetween(minEnginePower, maxEnginePower);
        } else if (minEnginePower != null) {
            cars = carRepository.findAllByEnginePowerGreaterThan(minEnginePower);
        } else if (maxEnginePower != null) {
            cars = carRepository.findAllByEnginePowerLessThan(maxEnginePower);
        } else {
            cars = carRepository.findAll();
        }

        return cars.stream()
                .map(carMapper::toDto)
                .toList();
    }

    @Transactional
    public CarDto createCar(CreateCarDto createCarDto) {
        Owner owner = ownerRepository.findByUsername(createCarDto.ownerUsername())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with username: " + createCarDto.ownerUsername()));

        Car car = carMapper.toEntity(createCarDto);
        car.setOwner(owner);
        car.setLastMaintenanceTimestamp(LocalDateTime.now());
        return carMapper.toDto(carRepository.save(car));
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));
        return carMapper.toDto(car);
    }

    @Transactional
    public CarDto updateCar(Long id, CreateCarDto createCarDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));

        car.setModel(createCarDto.model());
        car.setEnginePower(createCarDto.enginePower());
        car.setTorque(createCarDto.torque());

        return carMapper.toDto(carRepository.save(car));
    }

    @Transactional
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("Car not found with ID: " + id);
        }
        carRepository.deleteById(id);
    }
}