package com.care_health.care_health.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_image_room")
@Data
@Entity
public class ImageRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ImageRoomID", nullable = false)
    private UUID id;

    @Column(name = "FilePath", nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "typeImage_id")
    private TypeImage typeImage;

}
