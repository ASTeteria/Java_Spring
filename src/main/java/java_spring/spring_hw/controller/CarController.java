package java_spring.spring_hw.controller;


import java_spring.spring_hw.entity.Car;
import java_spring.spring_hw.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarRepository carRepository;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        return ResponseEntity.of(carRepository.findById(id));
    }

    @GetMapping(produces = "application/json")
    public List<Car> getCars(
            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
    ) {
        if (minEnginePower != null && maxEnginePower != null) {
            return carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower);

        }
        else if (minEnginePower != null) {
            return carRepository.findByEnginePowerBetween(minEnginePower, minEnginePower);
        }
        else if (maxEnginePower != null) {
            return carRepository.findByEnginePowerBetween(0, maxEnginePower);
        }
        else {
            return (List<Car>) carRepository.findAll();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carRepository.findById(id)
                .map(existingCar -> {
                    existingCar.setEnginePower(car.getEnginePower());
                    existingCar.setModel(car.getModel());
                    existingCar.setTorque(car.getTorque());
                    carRepository.save(existingCar);
                    return ResponseEntity.ok(existingCar);

                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
