package com.sm.shopmore.events.password;

import org.springframework.context.ApplicationListener;

public class ForgotPasswordEmailNotificationListener implements ApplicationListener<ForgotPasswordEvent> {
    @Override
    public void onApplicationEvent(ForgotPasswordEvent event) {

    }
}
