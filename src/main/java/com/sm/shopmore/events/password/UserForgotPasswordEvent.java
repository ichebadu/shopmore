package com.sm.shopmore.events.password;

import com.sm.shopmore.entity.User;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class UserForgotPasswordEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;

    public UserForgotPasswordEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
