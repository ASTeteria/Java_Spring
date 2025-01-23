package javaspring.springhw.service;

import jakarta.transaction.Transactional;
import javaspring.springhw.dto.OwnerDto;
import javaspring.springhw.entity.Owner;
import javaspring.springhw.mapper.OwnerMapper;
import javaspring.springhw.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public List<OwnerDto> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(ownerMapper::toDto)
                .toList();
    }

    @Transactional
    public OwnerDto createOwner(OwnerDto ownerDto) {
        Owner owner = ownerMapper.toEntity(ownerDto);
        return ownerMapper.toDto(ownerRepository.save(owner));
    }

    public OwnerDto getOwnerById(Long id) {
        return ownerRepository.findById(id)
                .map(ownerMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + id));
    }

    @Transactional
    public void deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + id));

        if (!owner.getCars().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete owner with associated cars. Remove cars first.");
        }

        ownerRepository.deleteById(id);
    }
}
