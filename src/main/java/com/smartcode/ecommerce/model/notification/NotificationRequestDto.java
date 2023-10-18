package com.smartcode.ecommerce.model.notification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Validated
public class NotificationRequestDto {

    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String description;

    @NotNull
    private Long notificationDate;

    private Long createDate;

    private String email;

}
