package javaspring.springhw.controller;

import jakarta.validation.Valid;
import javaspring.springhw.dto.CarDto;
import javaspring.springhw.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.createCar(carDto));
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @Valid @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.updateCar(id, carDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}