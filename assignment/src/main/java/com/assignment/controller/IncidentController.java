package com.assignment.controller;

import com.assignment.dto.IncidentDto;
import com.assignment.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Incident controller.
 */
@RestController()
@RequestMapping(path = "/incident")
@RequiredArgsConstructor
public class IncidentController {
    private final IncidentService incidentService;

    /**
     * Create incident incident dto.
     *
     * @param incidentDto the incident dto
     * @return the incident dto
     */
    @PostMapping(value = "/createIncident")
    public IncidentDto createIncident(@RequestBody IncidentDto incidentDto) {
        return incidentService.createIncident(incidentDto);
    }

    /**
     * List incident list.
     *
     * @return the list
     */
    @GetMapping(path = "/listIncidents")
    public List<IncidentDto> listIncident() {
        return incidentService.listIncident();
    }

    /**
     * List incident incident dto.
     *
     * @param id the id
     * @return the incident dto
     */
    @GetMapping(path = "/listIncidentById/{incidentId}")
    public IncidentDto listIncident(@PathVariable String incidentId) {
        return incidentService.listIncidentById(incidentId);
    }
}
