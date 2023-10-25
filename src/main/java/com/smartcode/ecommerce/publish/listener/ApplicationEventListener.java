package com.smartcode.ecommerce.publish.listener;


import com.smartcode.ecommerce.feign.AuditTrailFeign;
import com.smartcode.ecommerce.feign.NotificationFeign;
import com.smartcode.ecommerce.model.notification.EmailDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.publish.event.ActionEvent;
import com.smartcode.ecommerce.publish.event.RegistrationEvent;
import com.smartcode.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {

    private final NotificationFeign notificationFeign;
    private final UserRepository userRepository;
    private final AuditTrailFeign auditTrailFeign;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void registrationEvent(RegistrationEvent registrationEvent) {
        UserEntity user = userRepository.findByEmail(registrationEvent.getEmail());
        notificationFeign.sendEmail(new EmailDto(user.getEmail(), "Verification", user.getCode()));
    }


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void actionEvent(ActionEvent actionEvent) {
        auditTrailFeign.create(actionEvent.getActionRequestDto());
    }







}
