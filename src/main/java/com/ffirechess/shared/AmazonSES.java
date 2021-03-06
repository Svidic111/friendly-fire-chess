package com.ffirechess.shared;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.ffirechess.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AmazonSES {
    @Autowired
    private Environment env;

    // This address must be verified with Amazon SES.
    final String FROM = "ffirechess@gmail.com";

    // The subject line for the email.
    final String SUBJECT = "One last step to complete your registration with Friendly Fire Chess";

    final String PASSWORD_RESET_SUBJECT = "Password reset request";

    // The HTML body for the email.
    final String HTMLBODY = "<h1>Please verify your email address</h1>"
            + "<p>Thank you for registering with our web app. To complete registration process and be able to log in,"
            + " click on the following link: "
            //+ "<a href='http://localhost:8080/verification-service/email-verification.html?token=$tokenValue'>"
            + "<a href='http://localhost:8080/ffchess.verification-service/email-verification.html?token=$tokenValue'>"
            + "Final step to complete your registration" + "</a><br/><br/>"
            + "Thank you! And have fun!";

    // The email body for recipients with non-HTML email clients.
    final String TEXTBODY = "Please verify your email address. "
            + "Thank you for registering with our web app. To complete registration process and be able to log in,"
            + " open then the following URL in your browser window: "
            //+ "http://localhost:8080/verification-service/email-verification.html?token=$tokenValue"
            + " http://localhost:8080/ffchess.verification-service/email-verification.html?token=$tokenValue"
            + " Thank you! And have fun!";

    final String PASSWORD_RESET_HTMLBODY = "<h1>A request to reset your password.</h1>"
            + "<p>Hi, $nick!</p> "
            + "<p>Someone has requested to reset your password with our project. If it was not you, please ignore it."
            + " Otherwise please click on the link below to set a new password: "
            + "<a href='http://localhost:8080/ffchess.verification-service/password-reset.html?token=$tokenValue'>"
            + " Click this link to Reset Password"
            + "</a><br/><br/>"
            + "Thank you!";

    final String PASSWORD_RESET_TEXTBODY = "A request to reset your password "
            + "Hi, $nick! "
            + "Someone has requested to reset your password with our project. If it was not you, please ignore it."
            + " Otherwise please open the link below in your browser window to set a new password:"
            + " http://localhost:8080/ffchess.verification-service//password-reset.html?token=$tokenValue"
            + " Thank you!";

    public void verifyEmail(UserDto userDto) {

        // You can also set your keys this way. And it will work!
//        System.setProperty("aws.accessKeyId", env.getProperty("aws.accessKeyId"));
//        System.setProperty("aws.secretKey", env.getProperty("aws.secretKey"));

        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1)
                .build();

        String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
        String textBodyWithToken = TEXTBODY.replace("$tokenValue", userDto.getEmailVerificationToken());

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(userDto.getEmail()))
                .withMessage(new Message()
                        .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
                                .withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
                        .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
                .withSource(FROM);

        client.sendEmail(request);

        System.out.println("Email sent!");

    }

    public boolean sendPasswordResetRequest(String nick, String email, String token) {
        boolean returnValue = false;

        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

        String htmlBodyWithToken = PASSWORD_RESET_HTMLBODY.replace("$tokenValue", token);
        htmlBodyWithToken = htmlBodyWithToken.replace("$nick", nick);

        String textBodyWithToken = PASSWORD_RESET_TEXTBODY.replace("$tokenValue", token);
        textBodyWithToken = textBodyWithToken.replace("$nick", nick);

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email)).withMessage(new Message().withBody(new Body()
                .withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
                .withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
                .withSubject(new Content().withCharset("UTF-8").withData(PASSWORD_RESET_SUBJECT)))
                .withSource(FROM);

        SendEmailResult result = client.sendEmail(request);

        if (result != null && result.getMessageId() != null && !result.getMessageId().isEmpty()) {
            returnValue = true;
        }

        return returnValue;
    }

}
