package com.care_health.care_health.entity;

import com.care_health.care_health.enums.EStatusRoom;
import com.care_health.care_health.enums.ETypeRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_type_room")
@Data
@Entity
public class TypeRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TypeRoomID", nullable = false)
    private UUID id;

    @Column(name = "Name", nullable = false)
    private ETypeRoom name;

    @Column(name = "Description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "typeRoom")
    private List<Room> rooms;

}
