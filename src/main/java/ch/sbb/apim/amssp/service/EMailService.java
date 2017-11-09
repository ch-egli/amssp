package ch.sbb.apim.amssp.service;

import ch.sbb.apim.amssp.model.SimpleEMail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Describe
 *
 * @author Christian Egli
 * @since 11/9/17.
 */
@Service
public class EMailService {

    private JavaMailSender sender;
    private Configuration freemarkerConfig;

    public EMailService(JavaMailSender sender, Configuration freemarkerConfig) {
        this.sender = sender;
        this.freemarkerConfig = freemarkerConfig;
    }

    public void send(SimpleEMail mail) throws MessagingException, IOException, TemplateException {
        final String text = assembleBodyWithTemplate(mail);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(mail.getTo());
        //helper.setFrom(mail.getFrom());
        helper.setReplyTo("no-reply@sbb.ch");
        helper.setSubject(mail.getSubject());
        helper.setText(text, true); // set to html
        sender.send(message);
    }

    private String assembleBodyWithTemplate(SimpleEMail mail) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap();
        model.put("user", mail.getTo());

        // set loading location to src/main/resources/templates
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

        Template t = freemarkerConfig.getTemplate("welcome.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        return text;
    }

}
