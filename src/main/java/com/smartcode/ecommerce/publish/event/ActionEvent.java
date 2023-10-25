package com.smartcode.ecommerce.publish.event;

import com.smartcode.ecommerce.model.action.ActionDto;
import com.smartcode.ecommerce.model.action.ActionRequestDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class ActionEvent extends ApplicationEvent {

    private ActionRequestDto actionRequestDto;

    public ActionEvent(Object source, ActionRequestDto actionRequestDto) {
        super(source);
        this.actionRequestDto = actionRequestDto;
    }


}
