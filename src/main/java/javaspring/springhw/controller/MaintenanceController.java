package javaspring.springhw.controller;

import jakarta.validation.Valid;
import javaspring.springhw.dto.CreateMaintenanceDto;
import javaspring.springhw.dto.MaintenanceDto;
import javaspring.springhw.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    public ResponseEntity<List<MaintenanceDto>> getAllMaintenances() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances());
    }

    @PostMapping
    public ResponseEntity<MaintenanceDto> createMaintenance(@Valid @RequestBody CreateMaintenanceDto createMaintenanceDto) {
        return ResponseEntity.ok(maintenanceService.createMaintenance(createMaintenanceDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDto> getMaintenanceById(@PathVariable String id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceDto> updateMaintenance(
            @PathVariable String id,
            @Valid @RequestBody CreateMaintenanceDto createMaintenanceDto
    ) {
        return ResponseEntity.ok(maintenanceService.updateMaintenance(id, createMaintenanceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable String id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}