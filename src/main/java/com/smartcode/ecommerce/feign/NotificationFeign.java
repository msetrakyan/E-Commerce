package com.smartcode.ecommerce.feign;


import com.smartcode.ecommerce.configuration.NotificationFeignConfiguration;
import com.smartcode.ecommerce.model.notification.EmailDto;
import com.smartcode.ecommerce.model.notification.NotificationRequestDto;
import com.smartcode.ecommerce.model.notification.NotificationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(url = "http://localhost:8081/notification", configuration = NotificationFeignConfiguration.class)
public interface NotificationFeign {



    @PostMapping("/email")
     void sendEmail(@RequestBody EmailDto emailDto);

    @PostMapping
    void sendNotification(@RequestBody NotificationRequestDto email);

    @GetMapping("/ready")
    List<NotificationResponseDto> getReady(@RequestParam Integer id);

    @GetMapping("/waiting")
    List<NotificationResponseDto> getWaiting(@RequestParam Integer id);





}
