package com.assignment.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The type Incident dto.
 */
@Data
public class IncidentDto {
    private long id;
    private String incidentId;
    @ManyToOne
    private UserDto reporter;
    private String details;
    private LocalDateTime reportedAt;
    private String priority;
    @Pattern(regexp = "(?i)^(Closed|Open|InProgress)$", message = "Status must be one of: Closed, Open, InProgress")
    private String status;
    @Pattern(regexp = "(?i)^(Government|Enterprise)$", message = "Incident Type must be one of: Government or Enterprise")
    private String incidentType;
}
