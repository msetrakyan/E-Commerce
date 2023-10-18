package com.smartcode.ecommerce.model.notification;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationResponseDto {

    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    private String description;

    private Long notificationDate;

    private String email;

}
