package com.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The type Incident entity.
 */
@Entity
@Data
public class IncidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String incidentId;
    @ManyToOne
    private UserEntity reporter;
    private String details;
    private LocalDateTime reportedAt;
    private String priority;
    private String status;
    private String incidentType;

}
