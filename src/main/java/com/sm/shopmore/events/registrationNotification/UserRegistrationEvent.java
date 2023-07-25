package com.sm.shopmore.events.registrationNotification;

import com.sm.shopmore.entity.User;
import lombok.*;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class UserRegistrationEvent extends ApplicationEvent {
    private User user;
    private String otp;

    public UserRegistrationEvent(User user, String otp) {
        super(user);
        this.user = user;
        this.otp = otp;
    }
}
