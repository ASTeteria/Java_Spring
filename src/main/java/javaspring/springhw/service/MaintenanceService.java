package javaspring.springhw.service;

//import jakarta.transaction.Transactional;
//import javaspring.springhw.dto.CreateMaintenanceDto;
//import javaspring.springhw.dto.MaintenanceDto;
//import javaspring.springhw.entity.Maintenance;
//import javaspring.springhw.mapper.MaintenanceMapper;
//import javaspring.springhw.repository.MaintenanceRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MaintenanceService {
//
//    private final MaintenanceRepository maintenanceRepository;
//    private final MaintenanceMapper maintenanceMapper;
//
//    public List<MaintenanceDto> getAllMaintenances() {
//        return maintenanceRepository.findAll()
//                .stream()
//                .map(maintenanceMapper::toDto)
//                .toList();
//    }
//
//    @Transactional
//    public MaintenanceDto createMaintenance(CreateMaintenanceDto createMaintenanceDto) {
//        Maintenance maintenance = maintenanceMapper.toEntity(createMaintenanceDto);
//        maintenanceRepository.save(maintenance);
//
//        return maintenanceMapper.toDto(maintenance);
//    }
//
//    public MaintenanceDto getMaintenanceById(String id) {
//        return maintenanceRepository.findById(id)
//                .map(maintenanceMapper::toDto)
//                .orElseThrow(() -> new IllegalArgumentException("Maintenance not found with ID: " + id));
//    }
//
//    @Transactional
//    public MaintenanceDto updateMaintenance(String id, CreateMaintenanceDto createMaintenanceDto) {
//        Maintenance maintenance = maintenanceRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Maintenance not found with ID: " + id));
//
//        maintenance.setName(createMaintenanceDto.name());
//        maintenance.setDescription(createMaintenanceDto.description());
//        maintenance.setPrice(createMaintenanceDto.price());
//
//        return maintenanceMapper.toDto(maintenanceRepository.save(maintenance));
//    }
//
//    @Transactional
//    public void deleteMaintenance(String id) {
//        if (!maintenanceRepository.existsById(id)) {
//            throw new IllegalArgumentException("Maintenance not found with ID: " + id);
//        }
//        maintenanceRepository.deleteById(id);
//    }
//}

//import jakarta.transaction.Transactional;
//import javaspring.springhw.dto.CreateMaintenanceDto;
//import javaspring.springhw.dto.MaintenanceDto;
//import javaspring.springhw.entity.Maintenance;
//import javaspring.springhw.mapper.MaintenanceMapper;
//import javaspring.springhw.repository.MaintenanceRepository;
//import lombok.RequiredArgsConstructor;
//import org.bson.types.ObjectId;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MaintenanceService {
//
//    private final MaintenanceRepository maintenanceRepository;
//    private final MaintenanceMapper maintenanceMapper;
//
//    public List<MaintenanceDto> getAllMaintenances() {
//        return maintenanceRepository.findAll()
//                .stream()
//                .map(maintenanceMapper::mapToDto)
//                .toList();
//    }
//
//    @Transactional
//    public MaintenanceDto createMaintenance(CreateMaintenanceDto createMaintenanceDto) {
//        Maintenance maintenance = maintenanceMapper.mapToMaintenance(createMaintenanceDto);
//        maintenanceRepository.save(maintenance);
//        return maintenanceMapper.mapToDto(maintenance);
//    }
//
//    public MaintenanceDto getMaintenanceById(String id) {
//        return maintenanceRepository.findById(new ObjectId(id))
//                .map(maintenanceMapper::mapToDto)
//                .orElseThrow(() -> new IllegalArgumentException("Maintenance not found with ID: " + id));
//    }
//
//    @Transactional
//    public MaintenanceDto updateMaintenance(String id, CreateMaintenanceDto createMaintenanceDto) {
//        Maintenance maintenance = maintenanceRepository.findById(new ObjectId(id))
//                .orElseThrow(() -> new IllegalArgumentException("Maintenance not found with ID: " + id));
//
//        maintenance.setName(createMaintenanceDto.name());
//        maintenance.setDescription(createMaintenanceDto.description());
//        maintenance.setPrice(createMaintenanceDto.price());
//
//        return maintenanceMapper.mapToDto(maintenanceRepository.save(maintenance));
//    }
//
//    @Transactional
//    public void deleteMaintenance(String id) {
//        if (!maintenanceRepository.existsById(new ObjectId(id))) {
//            throw new IllegalArgumentException("Maintenance not found with ID: " + id);
//        }
//        maintenanceRepository.deleteById(new ObjectId(id));
//    }
//
//    public List<MaintenanceDto> getMaintenancesByName(String name) {
//        return maintenanceRepository.findAllByName(name)
//                .stream()
//                .map(maintenanceMapper::mapToDto)
//                .toList();
//    }
//
//    public List<MaintenanceDto> getRecentMaintenances(LocalDateTime from) {
//        return maintenanceRepository.findAllByCreatedAtAfter(from)
//                .stream()
//                .map(maintenanceMapper::mapToDto)
//                .toList();
//    }
//}

import javaspring.springhw.dto.CreateMaintenanceDto;
import javaspring.springhw.dto.MaintenanceDto;
import javaspring.springhw.entity.Maintenance;
import javaspring.springhw.mapper.MaintenanceMapper;
import javaspring.springhw.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    private final MaintenanceMapper maintenanceMapper;


    public MaintenanceDto createMaintenance(CreateMaintenanceDto dto) {
        Maintenance maintenance = maintenanceMapper.mapToMaintenance(dto);
        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        return maintenanceMapper.mapToDto(savedMaintenance);
    }

    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceRepository.findAll()
                .stream()
                .map(maintenanceMapper::mapToDto)
                .toList();
    }


    public List<MaintenanceDto> getMaintenancesByName(String name) {
        return maintenanceRepository.findAllByName(name)
                .stream()
                .map(maintenanceMapper::mapToDto)
                .toList();
    }


    public List<MaintenanceDto> getRecentMaintenances(LocalDateTime from) {
        return maintenanceRepository.findAllByCreatedAtAfter(from)
                .stream()
                .map(maintenanceMapper::mapToDto)
                .toList();
    }


    public Map<LocalDateTime, List<MaintenanceDto>> groupMaintenancesByDate(LocalDateTime from) {
        return maintenanceRepository.findAllByCreatedAtAfter(from)
                .stream()
                .collect(groupingBy(
                        Maintenance::getCreatedAt,
                        mapping(maintenanceMapper::mapToDto, toList())
                ));
    }
}