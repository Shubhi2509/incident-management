package com.assignment.service;

import com.assignment.dto.IncidentDto;
import com.assignment.dto.UserDto;
import com.assignment.entity.IncidentEntity;
import com.assignment.entity.UserEntity;
import com.assignment.exception.ResourceNotFoundException;
import com.assignment.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * The type Incident service.
 */
@Service
@RequiredArgsConstructor
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final ModelMapper modelMapper;

    /**
     * Create incident incident dto.
     *
     * @param incidentDto the incident dto
     * @return the incident dto
     */
    public IncidentDto createIncident(IncidentDto incidentDto) {
        incidentDto.setIncidentId(generateUniqueIncidentId());
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        incidentDto.setReporter(modelMapper.map(user, UserDto.class));
        incidentDto.setReportedAt(LocalDateTime.now());
        return modelMapper.map(incidentRepository.save(modelMapper.map(incidentDto, IncidentEntity.class)), IncidentDto.class);
    }

    /**
     * Generate incident id string.
     *
     * @return the string
     */
    public String generateIncidentId() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000;
        String currentYear = String.valueOf(Year.now().getValue());
        return "RMG" + randomNumber + currentYear;
    }

    /**
     * Generate unique incident id string.
     *
     * @return the string
     */
    public String generateUniqueIncidentId() {
        String incidentId;
        do {
            incidentId = generateIncidentId();
        } while (incidentRepository.existsByIncidentId(incidentId));
        return incidentId;
    }

    /**
     * List incident list.
     *
     * @return the list
     */
    public List<IncidentDto> listIncident() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<IncidentEntity> incidentEntityList = incidentRepository.findByReporter(user);
        return incidentEntityList.stream().map(in -> modelMapper.map(in, IncidentDto.class)).collect(Collectors.toList());
    }

    public IncidentDto listIncidentById(String id) {
        IncidentEntity incidents=incidentRepository.findByIncidentId(id).orElseThrow(()->new ResourceNotFoundException("No incident found with this incident id: " + id));
        return modelMapper.map(incidents, IncidentDto.class);
    }
}
