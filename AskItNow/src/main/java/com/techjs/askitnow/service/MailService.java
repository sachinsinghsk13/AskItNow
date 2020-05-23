package com.techjs.askitnow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.model.NotificationEmail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailService {
	
	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(NotificationEmail ne) {
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(ne.getRecipient());
			messageHelper.setSubject(ne.getSubject());
			messageHelper.setText(mailContentBuilder.build(ne.getBody()), true);
		};
		
		new Thread(() ->  {
			try {
				javaMailSender.send(preparator);
				log.info("Activation Mail Sent");
			} catch (MailException e) {
				log.error("mail not sent " + ne.getRecipient());
			}
		}).start();
	}
}
