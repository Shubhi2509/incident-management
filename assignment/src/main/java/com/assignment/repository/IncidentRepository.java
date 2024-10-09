package com.assignment.repository;

import com.assignment.dto.IncidentDto;
import com.assignment.entity.IncidentEntity;
import com.assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Incident repository.
 */
@Repository
public interface IncidentRepository extends JpaRepository<IncidentEntity, Long> {
    /**
     * Find by reporter list.
     *
     * @param user the user
     * @return the list
     */
    List<IncidentEntity> findByReporter(UserEntity user);

    Optional<IncidentEntity> findByIncidentId(String incidentId);

    boolean existsByIncidentId(String incidentId);
}
