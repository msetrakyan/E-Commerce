package com.smartcode.ecommerce.model.action;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActionFilterModel {


    private ActionType actionType;

    private String entityType;

    private LocalDateTime start;

    private LocalDateTime end;

    private Integer userId;



}
