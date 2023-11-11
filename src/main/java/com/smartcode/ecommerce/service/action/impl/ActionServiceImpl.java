package com.smartcode.ecommerce.service.action.impl;

import com.smartcode.ecommerce.model.action.ActionRequestDto;
import com.smartcode.ecommerce.model.action.ActionType;
import com.smartcode.ecommerce.service.action.ActionService;
import com.smartcode.ecommerce.service.producer.ProducerServiceActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ProducerServiceActivity producerServiceActivity;
    @Override
    @Transactional
    public void createAction(ActionType actionType, String entityType, Integer userId) {
        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionType(actionType);
        actionRequestDto.setEntityType(entityType);
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(userId);

        producerServiceActivity.sendMessage(actionRequestDto);
    }
}
