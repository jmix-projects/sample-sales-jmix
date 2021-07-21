package com.company.samplesales.screen.emailsending;

import io.jmix.email.EmailException;
import io.jmix.email.EmailInfo;
import io.jmix.email.EmailInfoBuilder;
import io.jmix.email.Emailer;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sales_EmailSending")
@UiDescriptor("email-sending.xml")
public class EmailSending extends Screen {
    @Autowired
    private Emailer emailer;

    @Subscribe("sendEmail")
    public void onSendEmailClick(Button.ClickEvent event) throws EmailException {
        EmailInfo emailInfo = EmailInfoBuilder.create("testaddress",
                "Email subject", "Email body")
                .build();
        emailer.sendEmail(emailInfo);
    }
}