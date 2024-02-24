package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.response.imageRoom.ImageRoomResponse;
import com.care_health.care_health.dtos.response.imageRoom.ListImageRoomResponse;
import com.care_health.care_health.entity.ImageRoom;
import com.care_health.care_health.entity.Room;
import com.care_health.care_health.entity.TypeImage;
import com.care_health.care_health.repositories.IImageRoomRepo;
import com.care_health.care_health.repositories.IRoomRepo;
import com.care_health.care_health.repositories.ITypeImageRepo;
import com.care_health.care_health.services.IServices.IImageRoomService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageRoomServiceImpl implements IImageRoomService {

    final private Cloudinary cloudinary;

    final private IRoomRepo roomRepo;

    final private ITypeImageRepo typeImageRepo;

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
    public ImageRoomResponse uploadImageRoom(List<MultipartFile> files, UUID roomID, UUID typeImageID) {
        try {
            Optional<Room> findRoomUploadImage = roomRepo.findById(roomID);
            Optional<TypeImage> findTypeImage = typeImageRepo.findById(typeImageID);

            List<String> urls = new ArrayList<>();

            for (MultipartFile file : files) {
                Map<String, String> options = new HashMap<>();
                options.put("publicId", roomID + "_" + typeImageID + "_" + UUID.randomUUID()); // Tạo publicId duy nhất cho mỗi ảnh
                options.put("folder", "room_images");

                Map result = cloudinary.uploader().upload(file.getBytes(), options);
                String url = (String) result.get("url");
                urls.add(url);
            }

            List<ImageRoom> imageRooms = new ArrayList<>();
            for (String url : urls) {
                ImageRoom imageRoom = new ImageRoom();
                imageRoom.setRoom(findRoomUploadImage.get());
                imageRoom.setTypeImage(findTypeImage.get());
                imageRoom.setFilePath(url);
                imageRooms.add(imageRoom);
            }

            List<ImageRoom> imageRoomsNewOfRoom = imageRoomRepo.saveAll(imageRooms);

            return ImageRoomResponse.builder()
                    .code(ResourceBundleConstant.IMGROOM_001)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.IMGROOM_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        } catch (Exception e) {
            // Xây dựng phản hồi lỗi
            return ImageRoomResponse.builder()
                    .code(ResourceBundleConstant.IMGROOM_002)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.IMGROOM_002))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .data(e)
                    .build();
        }
    }

    @Override
    public ListImageRoomResponse getListImageOfRoom(UUID roomID) {
        List<ImageRoom> listImageOfRoom = findImageByRoomId(roomID);

        List<String> imageUrls = listImageOfRoom.stream().map(ImageRoom::getFilePath).collect(Collectors.toList());

        if (listImageOfRoom != null) {
            return ListImageRoomResponse.builder()
                    .code(ResourceBundleConstant.IMGROOM_008)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .data(imageUrls)
                    .message(getMessageBundle(ResourceBundleConstant.IMGROOM_008))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
        return ListImageRoomResponse.builder()
                .code(ResourceBundleConstant.IMGROOM_009)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.IMGROOM_009))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public ListImageRoomResponse findImageByRoomIdAndTypeImageId(UUID roomId, UUID typeImageId) {

        List<ImageRoom> imageRoomOfTypeImage = imageRoomRepo.findImageByRoomIdAndTypeImageId(roomId, typeImageId);

        List<String> imageUrls = imageRoomOfTypeImage.stream().map(ImageRoom::getFilePath).collect(Collectors.toList());

        if (imageRoomOfTypeImage != null) {
            return ListImageRoomResponse.builder()
                    .code(ResourceBundleConstant.IMGROOM_010)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.IMGROOM_010))
                    .data(imageUrls)
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
        return ListImageRoomResponse.builder()
                .code(ResourceBundleConstant.IMGROOM_011)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.IMGROOM_011))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }
}
