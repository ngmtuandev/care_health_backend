package com.care_health.care_health.entity;

import com.care_health.care_health.enums.EStatusRoom;
import com.care_health.care_health.enums.ETypeRoom;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Name", nullable = false)
    private ETypeRoom name;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "IsDelete", nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;

}
