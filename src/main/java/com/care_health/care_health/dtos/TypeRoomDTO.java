package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import com.care_health.care_health.enums.ETypeRoom;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class TypeRoomDTO {

    private ETypeRoom name;

    private String description;

}
