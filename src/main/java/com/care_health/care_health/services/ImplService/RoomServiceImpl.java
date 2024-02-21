package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.entity.Coupon;
import com.care_health.care_health.entity.Facility;
import com.care_health.care_health.entity.Room;
import com.care_health.care_health.entity.TypeRoom;
import com.care_health.care_health.repositories.ICouponRepo;
import com.care_health.care_health.repositories.IFacilitiesRepo;
import com.care_health.care_health.repositories.IRoomRepo;
import com.care_health.care_health.repositories.ITypeRoomRepo;
import com.care_health.care_health.services.IServices.IRoomService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    final private IRoomRepo roomRepo;

    final private ITypeRoomRepo typeRoomRepo;

    final private IFacilitiesRepo facilitiesRepo;

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
            System.out.println("Have coupon " + roomCreateRequest.getCoupon());
            couponCurrent = couponRepo.findById(roomCreateRequest.getCoupon())
                    .orElseThrow(() -> new RuntimeException("Coupon Not Found"));
            System.out.println("Find success : " + couponCurrent.getId());
        }

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
                .build();
        Room result = roomRepo.save(newRoom);

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
}
