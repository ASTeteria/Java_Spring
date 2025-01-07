package javaspring.springhw.controller;

import jakarta.validation.Valid;
import javaspring.springhw.dto.CarDto;
import javaspring.springhw.dto.CreateCarDto;
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

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars(
            @RequestParam(required = false) Integer minEnginePower,
            @RequestParam(required = false) Integer maxEnginePower
    ) {
        return ResponseEntity.ok(carService.getCars(minEnginePower, maxEnginePower));
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CreateCarDto createCarDto) {
        return ResponseEntity.ok(carService.createCar(createCarDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(
            @PathVariable Long id,
            @Valid @RequestBody CreateCarDto createCarDto
    ) {
        return ResponseEntity.ok(carService.updateCar(id, createCarDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}