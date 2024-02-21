package com.care_health.care_health.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_facility")
@Data
@Entity
public class Facility extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "NameFacility", nullable = false)
    private int nameFacility;

    @Column(name = "Surcharge", nullable = false)
    private double surcharge;

    @Column(name = "IsNew", nullable = false)
    private boolean isNew;

    @ManyToMany(mappedBy = "facilities")
    private Set<Room> rooms = new HashSet<>();

}
