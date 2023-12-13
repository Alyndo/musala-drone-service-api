package com.droneservice.droneserviceapi.repository;

import com.droneservice.droneserviceapi.entity.Drone;
import com.droneservice.droneserviceapi.utils.enums.DroneState;
import com.droneservice.droneserviceapi.utils.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> findBySerialNumberAndEntityStatusIsNot(String serial, EntityStatus entityStatus);

    List<Drone> findByStateAndEntityStatusIsNotAndBatteryCapacityGreaterThan(DroneState state, EntityStatus entityStatus, Integer batteryCapacity);

    Boolean existsBySerialNumberAndEntityStatusIsNot(String serialNumber, EntityStatus entityStatus);

    long countByEntityStatusIsNot(EntityStatus entityStatus);
}
