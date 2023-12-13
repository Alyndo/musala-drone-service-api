package com.droneservice.droneserviceapi.repository;

import com.droneservice.droneserviceapi.entity.Drone;
import com.droneservice.droneserviceapi.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByDrone(Drone drone);
}
