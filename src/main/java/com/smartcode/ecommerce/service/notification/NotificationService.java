package com.smartcode.ecommerce.service.notification;



import com.smartcode.ecommerce.model.notification.NotificationRequestDto;
import com.smartcode.ecommerce.model.notification.NotificationResponseDto;

import java.util.List;

public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto notificationRequestDto);

    void createForRegistration(String code, Integer userId, String email);

    List<NotificationResponseDto> getWaiting();
    List<NotificationResponseDto> getReady();
}
