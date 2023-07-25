package com.sm.shopmore.events.password;

import org.springframework.context.ApplicationListener;

public class ForgotPasswordEmailNotificationListener implements ApplicationListener<UserForgotPasswordEvent> {
    @Override
    public void onApplicationEvent(UserForgotPasswordEvent event) {

    }
}
