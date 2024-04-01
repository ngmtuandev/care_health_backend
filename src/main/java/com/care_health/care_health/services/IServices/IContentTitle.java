package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.contentTitle.ContentTitleRequest;
import com.care_health.care_health.dtos.response.ContentTitle.ContentTitleRespose;

import java.util.UUID;

public interface IContentTitle {

    ContentTitleRespose createNewContent(ContentTitleRequest contentTitleRequest);

    ContentTitleRespose getAllContentTitle();

    ContentTitleRespose deleteItemContent(UUID contentTitleId);

    ContentTitleRespose deleteAllContentTitle();

}
