package ch.sbb.apim.amssp.controller;

import ch.sbb.apim.amssp.model.SimpleEMail;
import ch.sbb.apim.amssp.service.EMailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * TODO: Describe
 *
 * @author Christian Egli
 * @since 11/9/17.
 */
@RestController
public class EMailController {

    private EMailService eMailService;

    @Autowired
    public EMailController(EMailService eMailService) {
        this.eMailService = eMailService;
    }

    // cURL:
    // echo '{"to": "christian.egli4@gmail.com","from": "christian.egli@gmx.net","subject": "MailSender Test","body": "This is just a test..."}' | curl -H "Content-Type: application/json" -d @- http://localhost:8080/mail
    //
    @PostMapping("/mail")
    public String sendMail(@RequestBody SimpleEMail mail) {
        try {
            eMailService.send(mail);
            return "e-mail successfully sent";
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            return "Error in sending email: " + e;
        }
    }

}
