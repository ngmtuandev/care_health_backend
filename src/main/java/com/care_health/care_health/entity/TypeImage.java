package com.care_health.care_health.entity;

import com.care_health.care_health.enums.ETypeImage;
import com.care_health.care_health.enums.ETypeRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_type_image")
@Data
@Entity
public class TypeImage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TypeImageID", nullable = false)
    private UUID id;

    @Column(name = "Name", nullable = false)
    private ETypeImage name;

    @OneToMany(mappedBy = "typeImage")
    private List<ImageRoom> imageRooms;

}
