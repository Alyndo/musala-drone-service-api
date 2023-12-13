package com.droneservice.droneserviceapi.entity;

import com.droneservice.droneserviceapi.utils.enums.DroneModel;
import com.droneservice.droneserviceapi.utils.enums.DroneState;
import com.droneservice.droneserviceapi.utils.enums.DroneStateAction;
import com.droneservice.droneserviceapi.utils.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private Integer weightLimit;

    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Where(clause = "status='ACTIVE'")
    private List<Medication> medications;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15, nullable = false)
    private EntityStatus entityStatus;

    @Column(nullable = false, name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    void setState(DroneState state) {
        this.state = state;
    }

    @PrePersist
    public void init(){
        dateCreated = LocalDateTime.now();
        entityStatus = EntityStatus.ACTIVE;
        batteryCapacity = 100;
        DroneStateTransition.get().changeState(this, DroneStateAction.REGISTER);
    }

    @PreUpdate
    public void update(){
        dateModified = LocalDateTime.now();
    }
}
