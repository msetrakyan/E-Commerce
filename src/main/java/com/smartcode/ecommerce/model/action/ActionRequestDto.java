package com.smartcode.ecommerce.model.action;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionRequestDto {

    private ActionType actionType;

    private String entityType;

    private LocalDateTime actionDate;

    private Integer userId;

}
