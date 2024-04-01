package com.care_health.care_health.entity;

import com.care_health.care_health.enums.ETypeImage;
import com.care_health.care_health.enums.ETypeRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Name", nullable = false)
    @JsonIgnore
    private ETypeImage name;

    @OneToMany(mappedBy = "typeImage")
    @JsonIgnore
    private List<ImageRoom> imageRooms;

    @Column(name = "IsDelete", nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;

}
