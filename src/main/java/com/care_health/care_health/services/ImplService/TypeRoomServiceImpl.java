package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.TypeRoomDTO;
import com.care_health.care_health.dtos.request.typeRoom.TypeRoomCreateRequest;
import com.care_health.care_health.dtos.response.typeRoom.TypeRoomResponse;
import com.care_health.care_health.dtos.response.user.UserResponse;
import com.care_health.care_health.entity.TypeRoom;
import com.care_health.care_health.enums.ETypeRoom;
import com.care_health.care_health.repositories.ITypeRoomRepo;
import com.care_health.care_health.services.IServices.ITypeRoomService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeRoomServiceImpl implements ITypeRoomService {

    private final ITypeRoomRepo typeRoomRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    @Override
    public TypeRoom findByName(ETypeRoom name) {
        return typeRoomRepo.findByName(name);
    }

    @Override
    public TypeRoomResponse getListTypeRoom() {
        List<TypeRoom> listTypeRooms = typeRoomRepo.findAll();
        List<TypeRoomDTO> typeRoomsDTO = new ArrayList<>();
        listTypeRooms.stream().forEach(item -> {
            TypeRoomDTO typeRoomDTO = new TypeRoomDTO();
            typeRoomDTO.setDescription(item.getDescription());
            typeRoomDTO.setName(item.getName());
            typeRoomDTO.setId(item.getId());
            typeRoomsDTO.add(typeRoomDTO);
        });
        return TypeRoomResponse.builder()
                .code(ResourceBundleConstant.TYPEROOM_005)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.TYPEROOM_005))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(typeRoomsDTO)
                .build();
    }

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }


    @Override
    public TypeRoomResponse createTypeRoom(TypeRoomCreateRequest typeRoomCreateRequest) {

        TypeRoom findTypeRoom = findByName(typeRoomCreateRequest.getName());
        if (findTypeRoom != null) {
            return TypeRoomResponse.builder()
                    .code(ResourceBundleConstant.TYPEROOM_007)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.TYPEROOM_007))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        else {
            TypeRoom newTypeRoom = new TypeRoom();
            newTypeRoom.setDescription(typeRoomCreateRequest.getDescription());
            newTypeRoom.setName(typeRoomCreateRequest.getName());

            typeRoomRepo.save(newTypeRoom);

            return TypeRoomResponse.builder()
                    .code(ResourceBundleConstant.TYPEROOM_001)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.TYPEROOM_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .data(newTypeRoom)
                    .build();

        }
    }
}
