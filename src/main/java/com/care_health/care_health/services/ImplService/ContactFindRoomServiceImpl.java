package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.contactFindRoom.ContactFindRoomRequest;
import com.care_health.care_health.dtos.response.contactFindRoom.ContactFindRoomResponse;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.entity.ContactFindRoom;
import com.care_health.care_health.repositories.IContactFindRoomRepo;
import com.care_health.care_health.services.IServices.IContactFindRoomService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactFindRoomServiceImpl implements IContactFindRoomService {

    final private IContactFindRoomRepo contactFindRoomRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public ContactFindRoomResponse createNewContactFindRoom(ContactFindRoomRequest contactFindRoomRequest) {
        try {


            ContactFindRoom contactFindRoomNew = ContactFindRoom.builder()
                    .phone(contactFindRoomRequest.getPhone())
                    .descriptions(contactFindRoomRequest.getDescriptions())
                    .district(contactFindRoomRequest.getDistrict())
                    .rangePrice(contactFindRoomRequest.getRangePrice())
                    .timeStart(contactFindRoomRequest.getTimeStart())
                    .userName(contactFindRoomRequest.getUserName())
                    .build();

            ContactFindRoom savedContactFindRoom = contactFindRoomRepo.save(contactFindRoomNew);

            return ContactFindRoomResponse.builder()
                    .code(ResourceBundleConstant.CONTACT_FINDR_001)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.CONTACT_FINDR_001))
                    .data(savedContactFindRoom)
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();

        } catch (Exception ex) {
            return ContactFindRoomResponse.builder()
                    .code(ResourceBundleConstant.CONTACT_FINDR_002)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.CONTACT_FINDR_002))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .message(ex.getMessage())
                    .build();
        }
    }

    @Override
    public ContactFindRoomResponse getListContactFindRooms() {
        List<ContactFindRoom> contactFindRoomList = contactFindRoomRepo.findAll();
        return ContactFindRoomResponse.builder()
                .code(ResourceBundleConstant.CONTACT_FINDR_003)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.CONTACT_FINDR_003))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(contactFindRoomList)
                .build();
    }

    @Override
    public ContactFindRoomResponse getItemContactFindRoom(UUID contactFindRoomId) {
        Optional<ContactFindRoom> contactFindRoom = contactFindRoomRepo.findById(contactFindRoomId);

        return contactFindRoom.map(findRoom -> ContactFindRoomResponse.builder()
                .code(ResourceBundleConstant.CONTACT_FINDR_004)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.CONTACT_FINDR_004))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(findRoom)
                .build()).orElse(null);
    }
}
