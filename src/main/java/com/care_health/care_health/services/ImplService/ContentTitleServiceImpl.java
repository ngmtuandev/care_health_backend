package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.ContentTitleDTO;
import com.care_health.care_health.dtos.request.contentTitle.ContentTitleRequest;
import com.care_health.care_health.dtos.response.ContentTitle.ContentTitleRespose;
import com.care_health.care_health.dtos.response.contactFindRoom.ContactFindRoomResponse;
import com.care_health.care_health.entity.ContentTitle;
import com.care_health.care_health.repositories.IContentTitleRepo;
import com.care_health.care_health.services.IServices.IContentTitle;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentTitleServiceImpl implements IContentTitle {

    private final IContentTitleRepo contentTitleRepo;


    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public ContentTitleRespose createNewContent(ContentTitleRequest contentTitleRequest) {

        if (contentTitleRequest.getNameContent() != null) {
            ContentTitle contentTitle = new ContentTitle();
            contentTitle.setNameContent(contentTitleRequest.getNameContent());

            contentTitleRepo.save(contentTitle);

            return ContentTitleRespose.builder()
                    .code(ResourceBundleConstant.CONTENT_TITLE_01)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.CONTENT_TITLE_01))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();

        }

        return ContentTitleRespose.builder()
                .code(ResourceBundleConstant.CONTENT_TITLE_02)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.CONTENT_TITLE_02))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();

    }

    @Override
    public ContentTitleRespose getAllContentTitle() {

        List<ContentTitle> listContentTitle = contentTitleRepo.findAll();

        List<ContentTitleDTO> contentTitleDTOList = new ArrayList<>();
        listContentTitle.forEach(item -> {
            ContentTitleDTO contentTitleDTO = new ContentTitleDTO();
            contentTitleDTO.setNameContent(item.getNameContent());
            contentTitleDTOList.add(contentTitleDTO);
        });

        if (!contentTitleDTOList.isEmpty()) {
            return ContentTitleRespose.builder()
                    .code(ResourceBundleConstant.CONTENT_TITLE_03)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.CONTENT_TITLE_03))
                    .data(contentTitleDTOList)
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        return ContentTitleRespose.builder()
                .code(ResourceBundleConstant.CONTENT_TITLE_04)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.CONTENT_TITLE_04))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public ContentTitleRespose deleteItemContent(UUID contentTitleId) {
        return null;
    }

    @Override
    public ContentTitleRespose deleteAllContentTitle() {
        try {
            contentTitleRepo.deleteAll();
            return ContentTitleRespose.builder()
                    .code(ResourceBundleConstant.CONTENT_TITLE_05)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.CONTENT_TITLE_05))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
        catch (Exception e) {
            return ContentTitleRespose.builder()
                    .code(ResourceBundleConstant.CONTENT_TITLE_06)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.CONTENT_TITLE_06))
                    .data(e)
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
    }
}
