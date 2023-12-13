package com.droneservice.droneserviceapi.entity;

import com.droneservice.droneserviceapi.utils.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer weight;

    private String code;

    private String image;
    @ManyToOne()
    @JoinColumn(name="drone_id")
    private Drone drone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15, nullable = false)
    private EntityStatus entityStatus;

    @Column(nullable = false, name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @PrePersist
    public void init(){
        dateCreated = LocalDateTime.now();
        entityStatus = EntityStatus.ACTIVE;
    }

    @PreUpdate
    public void update(){
        dateModified = LocalDateTime.now();
    }
}
