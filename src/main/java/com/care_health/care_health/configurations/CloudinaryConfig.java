package com.care_health.care_health.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        // Thực hiện cấu hình Cloudinary ở đây
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dax8ja7ew",
                "api_key", "263149216563675",
                "api_secret", "oIk7pRqi7_qOhwNHZs-TnZUeluI"));
        return cloudinary;
    }

}
