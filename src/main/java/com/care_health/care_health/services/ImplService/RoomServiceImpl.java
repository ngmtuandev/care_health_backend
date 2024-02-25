package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.dtos.response.room.RoomDetailResponse;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.entity.*;
import com.care_health.care_health.repositories.*;
import com.care_health.care_health.services.IServices.IRoomService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    final private IRoomRepo roomRepo;

    final private ITypeRoomRepo typeRoomRepo;

    final private IFacilitiesRepo facilitiesRepo;

    final private IConvenientNearAreaRepo convenientNearAreaRepo;

    final private ICouponRepo couponRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public RoomResponse createRoom(RoomCreateRequest roomCreateRequest) {

        TypeRoom typeRoom = typeRoomRepo.findById(roomCreateRequest.getTypeRoom())
                .orElseThrow(() -> new RuntimeException("Type Room Not Found"));

        Set<Facility> facilities = roomCreateRequest.getFacilities().stream()
                .map(facilitiesID -> facilitiesRepo.findById(facilitiesID)
                        .orElseThrow(() -> new RuntimeException("Facilities Not Found")))
                .collect(Collectors.toSet());

        Coupon couponCurrent = null;
        if (roomCreateRequest.getCoupon() != null) {
            couponCurrent = couponRepo.findById(roomCreateRequest.getCoupon())
                    .orElseThrow(() -> new RuntimeException("Coupon Not Found"));
        }

        ConvenientNearArea convenientNearArea = new ConvenientNearArea();
        convenientNearArea.setDistance(roomCreateRequest.getConvenientNearArea().getDistance());
        convenientNearArea.setName(roomCreateRequest.getConvenientNearArea().getName());

        ConvenientNearArea newConvenientNearArea = convenientNearAreaRepo.save(convenientNearArea);

        Room newRoom = Room.builder()
                .coupon(couponCurrent)
                .description(roomCreateRequest.getDescription())
                .district(roomCreateRequest.getDistrict())
                .leaseTerm(roomCreateRequest.getLeaseTerm())
                .numberPerson(roomCreateRequest.getNumberPerson())
                .location(roomCreateRequest.getLocation())
                .price(roomCreateRequest.getPrice())
                .stakeMoney(roomCreateRequest.getStakeMoney())
                .typeRoom(typeRoom)
                .title(roomCreateRequest.getTitle())
                .statusRoom(roomCreateRequest.getStatusRoom())
                .facilities(facilities)
                .convenientNearArea(convenientNearArea)
                .build();
        Room result = roomRepo.save(newRoom);

        convenientNearArea.setRoom(newRoom);
        convenientNearAreaRepo.save(newConvenientNearArea);


        if (result != null) {

            return RoomResponse.builder()
                    .code(ResourceBundleConstant.ROM_001)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.ROM_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
        return RoomResponse.builder()
                .code(ResourceBundleConstant.ROM_002)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.ROM_002))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public List<RoomResponse> getAllRoomd() {
        return null;
    }

    @Override
    public RoomResponse getItemRoomId(UUID roomId) {

        Room findRoom = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room Not Found"));

        RoomDetailResponse responseRoom = RoomDetailResponse.builder()
                .id(findRoom.getId())
                .price(findRoom.getPrice())
                .numberPerson(findRoom.getNumberPerson())
                .description(findRoom.getDescription())
                .title(findRoom.getTitle())
                .stakeMoney(findRoom.getStakeMoney())
                .location(findRoom.getLocation())
                .district(findRoom.getDistrict())
                .leaseTerm(findRoom.getLeaseTerm())
//                .facilities(findRoom.getFacilities())
                .statusRoom(findRoom.getStatusRoom())
                .build();
        if (responseRoom != null) {
            Optional<Coupon> coupon = couponRepo.findById(findRoom.getCoupon().getId());
            if (findRoom.getCoupon() != null) {
                Coupon couponOptional = couponRepo.findById(findRoom.getCoupon().getId())
                        .orElse(null);
                responseRoom.setCoupon(couponOptional);
            }
//            if (findRoom.getFacilities() != null) {
//                System.out.println("getFacilities:" + findRoom.getFacilities());
//            }
            if (findRoom.getConvenientNearArea() != null) {
                ConvenientNearArea convenientNearArea = convenientNearAreaRepo.findById(findRoom.getConvenientNearArea().getId())
                        .orElse(null);
                responseRoom.setConvenientNearArea(convenientNearArea);
            }
            if (findRoom.getTypeRoom() != null) {
                TypeRoom typeRoom = typeRoomRepo.findById(findRoom.getTypeRoom().getId())
                        .orElse(null);
                responseRoom.setTypeRoom(typeRoom);
            }
        }

        return RoomResponse.builder()
                .code(ResourceBundleConstant.ROM_007)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.ROM_007))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(responseRoom)
                .build();
    }
}
