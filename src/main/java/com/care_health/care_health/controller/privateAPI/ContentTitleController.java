package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.ContentTitleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.contentTitle.ContentTitleRequest;
import com.care_health.care_health.dtos.response.ContentTitle.ContentTitleRespose;
import com.care_health.care_health.services.ImplService.ContentTitleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + ContentTitleConstant.API_CONTENT_TITLE)

public class ContentTitleController {

    @Autowired
    ContentTitleServiceImpl contentTitleService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(ContentTitleConstant.API_CONTENT_TITLE_CREATE)
    public ResponseEntity<ContentTitleRespose> createContentTitle(@RequestBody ContentTitleRequest contentTitleRequest) {
        ContentTitleRespose result = contentTitleService.createNewContent(contentTitleRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ContentTitleConstant.API_CONTENT_TITLE_GETS)
    public ResponseEntity<ContentTitleRespose> getListContentTitle() {
        ContentTitleRespose result = contentTitleService.getAllContentTitle();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(ContentTitleConstant.API_CONTENT_TITLE_DELETE)
    public ResponseEntity<ContentTitleRespose> deleteContentTitle() {
        ContentTitleRespose result = contentTitleService.deleteAllContentTitle();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
