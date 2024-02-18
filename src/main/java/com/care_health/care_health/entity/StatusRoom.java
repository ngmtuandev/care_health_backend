package com.care_health.care_health.entity;

import com.care_health.care_health.enums.EStatusRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_status_room")
@Data
@Entity
public class StatusRoom extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "StatusRoomID", nullable = false)
    private UUID id;

    @Column(name = "NameStatus", nullable = false)
    private EStatusRoom nameStatus;

    @OneToMany(mappedBy = "statusRoom")
    private List<Room> rooms;


}
