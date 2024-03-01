package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.room.ConditionFindRoom;
import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.request.room.RoomEditRequest;
import com.care_health.care_health.dtos.request.room.RoomUpdateStatusRequest;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.dtos.response.imageRoom.ListImageRoomResponse;
import com.care_health.care_health.dtos.response.room.RoomDetailResponse;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.entity.*;
import com.care_health.care_health.enums.EStatusRoom;
import com.care_health.care_health.repositories.*;
import com.care_health.care_health.services.IServices.IRoomService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    final private IImageRoomRepo imageRoomRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }


    @Override
    public List<ImageRoom> findImageByRoomId(UUID roomId) {
        return imageRoomRepo.findByRoomId(roomId);
    }

    @Override
    public RoomResponse updateStatusRoom(UUID roomId, EStatusRoom eStatusRoom) {

        Room roomRented = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room Not Found"));

        roomRented.setStatusRoom(eStatusRoom);
        roomRepo.save(roomRented);

        return RoomResponse.builder()
                .code(ResourceBundleConstant.ROM_003)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.ROM_003))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public RoomResponse editRoom(UUID roomEditId, RoomEditRequest roomEditRequest) {

        Optional<Room> roomEdit = roomRepo.findById(roomEditId);

        try {
            if (roomEdit.isPresent()) {
                Room room = roomEdit.get();

                TypeRoom typeRoom = typeRoomRepo.findById(roomEditRequest.getTypeRoom())
                        .orElseThrow(() -> new RuntimeException("Type Room Not Found"));

                Set<Facility> facilities = roomEditRequest.getFacilities().stream()
                        .map(facilitiesID -> facilitiesRepo.findById(facilitiesID)
                                .orElseThrow(() -> new RuntimeException("Facilities Not Found")))
                        .collect(Collectors.toSet());

                Coupon couponCurrent = null;
                if (roomEditRequest.getCoupon() != null) {
                    couponCurrent = couponRepo.findById(roomEditRequest.getCoupon())
                            .orElseThrow(() -> new RuntimeException("Coupon Not Found"));
                }

                ConvenientNearArea convenientNearArea = new ConvenientNearArea();
                convenientNearArea.setDistance(roomEditRequest.getConvenientNearArea().getDistance());
                convenientNearArea.setName(roomEditRequest.getConvenientNearArea().getName());
                ConvenientNearArea newConvenientNearArea = convenientNearAreaRepo.save(convenientNearArea);


                room.setTypeRoom(typeRoom);
                room.setStatusRoom(roomEditRequest.getStatusRoom());
                room.setCoupon(couponCurrent);
                room.setDescription(roomEditRequest.getDescription());
                room.setConvenientNearArea(newConvenientNearArea);
                room.setDistrict(roomEditRequest.getDistrict());
                room.setLocation(roomEditRequest.getLocation());
                room.setFacilities(facilities);
                room.setTitle(roomEditRequest.getTitle());
                room.setPrice(roomEditRequest.getPrice());
                room.setNumberPerson(roomEditRequest.getNumberPerson());
                room.setStakeMoney(roomEditRequest.getStakeMoney());
                room.setLeaseTerm(roomEditRequest.getLeaseTerm());
                room.setStatusRoom(roomEditRequest.getStatusRoom());

                roomRepo.save(room);

                return RoomResponse.builder()
                        .code(ResourceBundleConstant.ROM_011)
                        .status(SystemConstant.STATUS_CODE_SUCCESS)
                        .message(getMessageBundle(ResourceBundleConstant.ROM_011))
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .build();

            }
        } catch (Exception ex) {
            return RoomResponse.builder()
                    .code(ResourceBundleConstant.ROM_012)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.ROM_012))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .message(ex.getMessage())
                    .build();
        }

        return RoomResponse.builder()
                .code(ResourceBundleConstant.ROM_012)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.ROM_012))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();

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
                    .data(result.getId())
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
    public RoomResponse getAllRooms(Integer page, Integer size) {

        List<RoomDetailResponse> listRooms = new ArrayList<>();

        PageRequest pageable = PageRequest.of(page, size);
        Page<Room> rooms= roomRepo.findAll(pageable);

        int totalPages = rooms.getTotalPages();

        if (!rooms.isEmpty()) {
            rooms.stream().forEach(item -> {

                List<ImageRoom> listImageOfRoom = findImageByRoomId(item.getId());
                List<String> imageUrls = listImageOfRoom.stream().map(ImageRoom::getFilePath).collect(Collectors.toList());

                RoomDetailResponse responseRoom = RoomDetailResponse.builder()
                        .id(item.getId())
                        .price(item.getPrice())
                        .numberPerson(item.getNumberPerson())
                        .description(item.getDescription())
                        .title(item.getTitle())
                        .stakeMoney(item.getStakeMoney())
                        .location(item.getLocation())
                        .district(item.getDistrict())
                        .leaseTerm(item.getLeaseTerm())
                        .statusRoom(item.getStatusRoom())
                        .images(imageUrls)
                        .build();
                if (responseRoom != null) {
                    Optional<Coupon> coupon = couponRepo.findById(item.getCoupon().getId());
                    if (item.getCoupon() != null) {
                        Coupon couponOptional = couponRepo.findById(item.getCoupon().getId())
                                .orElse(null);
                        responseRoom.setCoupon(couponOptional);
                    }
                    if (item.getFacilities() != null) {
                        responseRoom.setFacilities(item.getFacilities());
                    }
                    if (item.getConvenientNearArea() != null) {
                        ConvenientNearArea convenientNearArea = convenientNearAreaRepo.findById(item.getConvenientNearArea().getId())
                                .orElse(null);
                        responseRoom.setConvenientNearArea(convenientNearArea);
                    }
                    if (item.getTypeRoom() != null) {
                        TypeRoom typeRoom = typeRoomRepo.findById(item.getTypeRoom().getId())
                                .orElse(null);
                        responseRoom.setTypeRoom(typeRoom);
                    }
                }
                listRooms.add(responseRoom);
            });
            return RoomResponse.builder()
                    .code(ResourceBundleConstant.ROM_008)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.ROM_008))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .data(listRooms)
                    .totalPages(totalPages)
                    .build();
        }

        return RoomResponse.builder()
                .code(ResourceBundleConstant.ROM_005)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.ROM_005))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(listRooms)
                .build();
    }

    @Override
    public RoomResponse getItemRoomId(UUID roomId) {

        Room findRoom = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room Not Found"));

        List<ImageRoom> listImageOfRoom = findImageByRoomId(roomId);

        List<String> imageUrls = listImageOfRoom.stream().map(ImageRoom::getFilePath).collect(Collectors.toList());


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
                .statusRoom(findRoom.getStatusRoom())
                .images(imageUrls)
                .build();
        if (responseRoom != null) {
            Optional<Coupon> coupon = couponRepo.findById(findRoom.getCoupon().getId());
            if (findRoom.getCoupon() != null) {
                Coupon couponOptional = couponRepo.findById(findRoom.getCoupon().getId())
                        .orElse(null);
                responseRoom.setCoupon(couponOptional);
            }
            if (findRoom.getFacilities() != null) {
                responseRoom.setFacilities(findRoom.getFacilities());
            }
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

    @Override
    public RoomResponse findRoomByCondition(ConditionFindRoom conditionFindRoom) {
        System.out.println("roomByConditions Start");
        List<Room> roomByConditions = roomRepo.roomsByCondition(conditionFindRoom);

        List<RoomDetailResponse> responseRooms = roomByConditions.stream()
                .map(room -> buildRoomDetailResponseWithImages(room))
                .collect(Collectors.toList());

        if (!roomByConditions.isEmpty()) {
            return RoomResponse.builder()
                    .code(ResourceBundleConstant.ROM_008)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.ROM_008))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .data(responseRooms)
                    .build();
        }
        return RoomResponse.builder()
                .code(ResourceBundleConstant.ROM_010)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.ROM_010))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    private RoomDetailResponse buildRoomDetailResponseWithImages(Room room) {
        List<ImageRoom> listImageOfRoom = findImageByRoomId(room.getId());
        List<String> imageUrls = listImageOfRoom.stream().map(ImageRoom::getFilePath).collect(Collectors.toList());

        RoomDetailResponse responseRoom = RoomDetailResponse.builder()
                .id(room.getId())
                .price(room.getPrice())
                .numberPerson(room.getNumberPerson())
                .description(room.getDescription())
                .title(room.getTitle())
                .stakeMoney(room.getStakeMoney())
                .location(room.getLocation())
                .district(room.getDistrict())
                .leaseTerm(room.getLeaseTerm())
                .statusRoom(room.getStatusRoom())
                .images(imageUrls)
                .build();

        // Set other details like coupon, facilities, convenientNearArea, and typeRoom as before
        if (responseRoom != null) {
            Optional<Coupon> coupon = couponRepo.findById(room.getCoupon().getId());
            if (room.getCoupon() != null) {
                Coupon couponOptional = couponRepo.findById(room.getCoupon().getId()).orElse(null);
                responseRoom.setCoupon(couponOptional);
            }
            if (room.getFacilities() != null) {
                responseRoom.setFacilities(room.getFacilities());
            }
            if (room.getConvenientNearArea() != null) {
                ConvenientNearArea convenientNearArea = convenientNearAreaRepo.findById(room.getConvenientNearArea().getId()).orElse(null);
                responseRoom.setConvenientNearArea(convenientNearArea);
            }
            if (room.getTypeRoom() != null) {
                TypeRoom typeRoom = typeRoomRepo.findById(room.getTypeRoom().getId()).orElse(null);
                responseRoom.setTypeRoom(typeRoom);
            }
        }
        return responseRoom;
    }

}
