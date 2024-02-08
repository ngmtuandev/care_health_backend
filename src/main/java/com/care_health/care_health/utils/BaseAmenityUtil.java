package com.care_health.care_health.utils;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Component
public class BaseAmenityUtil {

    public String getMessageBundle(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("care_health");
        return resourceBundle.getString(key);
    }

    public long currentTimeSeconds() {
        long currentTimeMillis = System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis);
    }

}
