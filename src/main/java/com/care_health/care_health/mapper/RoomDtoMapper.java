package com.care_health.care_health.mapper;

import com.care_health.care_health.dtos.RoomDTO;
import com.care_health.care_health.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RoomDtoMapper implements Function<Room, RoomDTO> {
    @Override
    public RoomDTO apply(Room room) {
        return RoomDTO.builder()
                .price(room.getPrice())
                .district(room.getDistrict())
                .description(room.getDescription())
                .location(room.getLocation())
                .numberPerson(room.getNumberPerson())
                .leaseTerm(room.getLeaseTerm())
                .stakeMoney(room.getStakeMoney())
                .build();
    }
}
