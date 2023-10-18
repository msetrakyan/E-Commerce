package com.smartcode.ecommerce.publish.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {

    private String email;


    public RegistrationEvent(Object source, String email) {
        super(source);
        this.email = email;
    }






}
