package com.smartcode.ecommerce.service.action;


import com.smartcode.ecommerce.model.action.ActionType;

public interface ActionService {
    void createAction(ActionType actionType, String entityType, Integer userId);
}
