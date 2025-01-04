package java_spring.spring_hw.controller;

import java_spring.spring_hw.config.FuelConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class ReferenceDataController {

    @Value("${engine-types}")
    private List<String> engineTypes;

    private final FuelConfig fuelConfig;

    // engine-types
    @GetMapping("/engine-types")
    public List<String> getEngineTypes() {
        return engineTypes;
    }

    // fuel-types
    @GetMapping("/fuel-types")
    public Map<String, List<String>> getFuelTypes() {
        return fuelConfig.getFuelTypes();
    }

    // fuel-types/{fuelName}

    @GetMapping("/fuel-types/{fuelName}")
    public List<String> getFuelTypeByName(@PathVariable String fuelName) {
        List<String> options = fuelConfig.getFuelTypes().get(fuelName);
        if (options == null) {
            throw new IllegalArgumentException("Тип палива не знайдено: " + fuelName);
        }
        return options;
    }


}
