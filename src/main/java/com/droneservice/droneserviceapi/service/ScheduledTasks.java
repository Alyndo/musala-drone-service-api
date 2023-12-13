package com.droneservice.droneserviceapi.service;

import com.droneservice.droneserviceapi.entity.Drone;
import com.droneservice.droneserviceapi.entity.DroneStateTransition;
import com.droneservice.droneserviceapi.repository.DroneRepository;
import com.droneservice.droneserviceapi.utils.enums.DroneState;
import com.droneservice.droneserviceapi.utils.enums.DroneStateAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
@EnableAsync
@Slf4j
public class ScheduledTasks {
    private final DroneRepository droneRepository;

    @Value("${limit.drone.battery}")
    private int batteryLimit;

    @Async
    @Scheduled(cron = "0/10 * * * * *") // every 10 seconds
    public void checkDronesBatteryLevel() {
        List<Drone> drones = droneRepository.findAll();
        drones.forEach(drone -> {
            log.info("Drone Battery Capacity Check [serialNumber: {}, state: {}, batteryCapacity : {}%",
                    drone.getSerialNumber(), drone.getState(), drone.getBatteryCapacity());
            if (drone.getBatteryCapacity() < batteryLimit) {
                if (drone.getState().equals(DroneState.DELIVERING)) {
                    DroneStateTransition.get().changeState(drone, DroneStateAction.RETURN);
                    log.info("Drone[serialNumber : {} ] Battery Capacity is too low, state is now set to [RETURNING]",
                            drone.getSerialNumber(), drone.getState(), drone.getSerialNumber());
                }
            }
            droneRepository.save(drone);
        });
    }

    @Async
    @Scheduled(cron = "0/10 * * * * *") // every 10 seconds
    public void chargeAndDischargeDroneBatteries() {
        List<Drone> drones = droneRepository.findAll();
        drones.forEach(drone -> {
            if (drone.getState().equals(DroneState.IDLE)) {
                chargeBattery(drone);
            }
            if(drone.getState().equals(DroneState.DELIVERING) || drone.getState().equals(DroneState.RETURNING)){
                discharge(drone);
            }
            droneRepository.save(drone);
        });
    }

    private void discharge(Drone drone) {
        int batteryPercentage = drone.getBatteryCapacity();
        drone.setBatteryCapacity(Math.max(batteryPercentage - 5, 0));
    }

    private void chargeBattery(Drone drone) {
        int batteryPercentage = drone.getBatteryCapacity();
        drone.setBatteryCapacity(Math.min(batteryPercentage + 5, 100));
    }
}
