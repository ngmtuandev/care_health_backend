package com.care_health.care_health.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private String nameFacility;

    @Column(name = "Surcharge", nullable = false)
    private double surcharge;

    @Column(name = "IsNew", nullable = false)
    private boolean isNew;

    @ManyToMany(mappedBy = "facilities", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Room> rooms = new HashSet<>();

    @Column(name = "IsDelete", nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isNew() {
        return isNew;
    }
}
