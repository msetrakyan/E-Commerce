package com.smartcode.ecommerce.service.notification.impl;

import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.feign.NotificationFeign;
import com.smartcode.ecommerce.model.notification.NotificationRequestDto;
import com.smartcode.ecommerce.model.notification.NotificationResponseDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.notification.NotificationService;
import com.smartcode.ecommerce.service.producer.ProducerServiceNotification;
import com.smartcode.ecommerce.util.CurrentUser;
import com.smartcode.ecommerce.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFeign notificationFeignClient;
    private final UserRepository userRepository;
    private final ProducerServiceNotification producerServiceNotification;

    @Override
    @Transactional
    public NotificationResponseDto create(NotificationRequestDto notificationRequestDto) {
        UserEntity userEntity = userRepository.findById(CurrentUser.getId()).orElseThrow(() -> new ResourceNotFoundException(Message.USER_NOT_FOUND));

        notificationRequestDto.setCreateDate(System.currentTimeMillis());
        notificationRequestDto.setEmail(userEntity.getEmail());
        notificationRequestDto.setUserId(CurrentUser.getId());
         notificationFeignClient.sendNotification(notificationRequestDto);
         return null;
    }

    @Override
    @Transactional
    public void createForRegistration(String code, Integer userId, String email) {
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
        notificationRequestDto.setTitle(Message.EMAIL_SUBJECT);
        notificationRequestDto.setContent(Message.EMAIL_MESSAGE + code);
        notificationRequestDto.setEmail(email);
        notificationRequestDto.setUserId(userId);

        notificationFeignClient.sendNotification(notificationRequestDto);
    }

    @Override
    @Transactional
    public List<NotificationResponseDto> getWaiting() {
        return notificationFeignClient.getWaiting(CurrentUser.getId());
    }

    @Override
    @Transactional
    public List<NotificationResponseDto> getReady() {
        return notificationFeignClient.getReady(CurrentUser.getId());
    }
}
