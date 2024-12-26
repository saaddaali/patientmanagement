package ma.zyn.app.config.transverse.emailling;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    /*@Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String email;*/

    public void sendSimpleMessage(EmailRequest emailRequest) {

        System.out.println("sending email");
        System.out.println(emailRequest.getBody());

        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom(email);
        message.setTo(emailRequest.getTo());
        message.setText(emailRequest.getBody());
        message.setCc(emailRequest.getCc());
        message.setBcc(emailRequest.getBcc());
        message.setSubject(emailRequest.getSubject());

        //mailSender.send(message);

        System.out.println("mail sent!");

    }
}
