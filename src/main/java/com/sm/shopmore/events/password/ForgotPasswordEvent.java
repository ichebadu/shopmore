package com.sm.shopmore.events.password;

import com.sm.shopmore.entity.User;
import org.springframework.context.ApplicationEvent;

public class ForgotPasswordEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    public ForgotPasswordEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
