package javaspring.springhw.service;

import jakarta.transaction.Transactional;
import javaspring.springhw.dto.CreateMaintenanceDto;
import javaspring.springhw.dto.MaintenanceDto;
import javaspring.springhw.entity.Maintenance;
import javaspring.springhw.mapper.MaintenanceMapper;
import javaspring.springhw.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceRepository.findAll()
                .stream()
                .map(maintenanceMapper::toDto)
                .toList();
    }

    @Transactional
    public MaintenanceDto createMaintenance(CreateMaintenanceDto createMaintenanceDto) {
        Maintenance maintenance = maintenanceMapper.toEntity(createMaintenanceDto);
        maintenanceRepository.save(maintenance);

        return maintenanceMapper.toDto(maintenance);
    }

    public MaintenanceDto getMaintenanceById(String id) {
        return maintenanceRepository.findById(id)
                .map(maintenanceMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance not found with ID: " + id));
    }

    @Transactional
    public MaintenanceDto updateMaintenance(String id, CreateMaintenanceDto createMaintenanceDto) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance not found with ID: " + id));

        maintenance.setName(createMaintenanceDto.name());
        maintenance.setDescription(createMaintenanceDto.description());
        maintenance.setPrice(createMaintenanceDto.price());

        return maintenanceMapper.toDto(maintenanceRepository.save(maintenance));
    }

    @Transactional
    public void deleteMaintenance(String id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new IllegalArgumentException("Maintenance not found with ID: " + id);
        }
        maintenanceRepository.deleteById(id);
    }
}