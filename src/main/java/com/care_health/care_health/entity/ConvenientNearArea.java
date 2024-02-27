package com.care_health.care_health.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_convenient_near_area")
@Data
@Entity
public class ConvenientNearArea extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "Distance", nullable = false)
    private int distance;

    @Column(name = "Name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @Column(name = "IsDelete", nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;

}
