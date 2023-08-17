package com.sm.shopmore.events.password;

import com.sm.shopmore.entity.ConfirmationToken;
import com.sm.shopmore.entity.User;
import com.sm.shopmore.service.ConfirmationService;
import com.sm.shopmore.utils.RandomGeneratedValue;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class ForgotPasswordEmailNotificationListener implements ApplicationListener<UserForgotPasswordEvent> {
    private final ConfirmationService confirmationService;
    private final JavaMailSender mailSender;
    @Override
    public void onApplicationEvent(UserForgotPasswordEvent event) {
        User user = event.getUser();
        String resetToken =  UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(resetToken,user);
        confirmationService.saveOtp(confirmationToken);

        String url = event.getApplicationUrl() + "/api/v1/otp/verify-password-token?token=" + resetToken;
        System.out.println(url);
        try{
            sendConfirmationLink(user, url );
        }catch(MessagingException e){
            throw new RuntimeException(e.getMessage());
        }


    }


    private void sendConfirmationLink(User user,String url) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        messageHelper.setFrom("chukwu.iche@gmail.com");
        messageHelper.setSubject("Password Reset Request");
        messageHelper.setTo(user.getEmail());

        String senderName = "Shopmore Hub Inc";
        String mailContent = "<p> Hi, "+user.getFirstName()+" </p>"+
                "<p> Welcome to Shopmore Hub Inc. </p>"+
                "<p>Please, follow the link below to reset your password. </p>" +
                "<a href=\""+url+"\"> Click here</a>" +
                "<p> Thank you <br>" + senderName;
        messageHelper.setText(mailContent, true);
        mailSender.send(mimeMessage);


        log.info(url);
        log.info("Sent token ", user);

    }
}
