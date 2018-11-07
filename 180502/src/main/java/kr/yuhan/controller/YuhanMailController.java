package kr.yuhan.controller;

import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YuhanMailController 
{
	@Autowired
	JavaMailSender mailSender;
	
	@RequestMapping(value = "/contact", method=RequestMethod.GET)
	public void contact()
	{ 
		
	}
	
	@RequestMapping(value = "/contact", method=RequestMethod.POST)
	public String contactPost(HttpServletRequest request) throws Exception
	{ 
		String setfrom = request.getParameter("setfrom").toString();        
	    String tomail  = "yuhanitsystem16@gmail.com";     // 받는 사람 이메일
	    String title   = request.getParameter("title").toString();      // 제목
	    String content = request.getParameter("content").toString();    // 내용
	    
	    String charSet = "utf-8";
	      String host = "mw-002.cafe24.com";
	      int   port = 587;
	      String SMTPuser = "yuhanitsystem16@ouk3333.pe.kr";
	      String password = "itsoftware16";
	      String toEmail = "yuhanitsystem16@gmail.com"; // 받는사람
	      String fromEmail = SMTPuser; // 보내는사람 이메일
	      
	      HtmlEmail email = new HtmlEmail();
	      email.setDebug(false);
	      email.setCharset(charSet);
	      email.setSSL(false);
	      email.setHostName(host);
	      email.setSmtpPort(port);
	      email.setAuthentication(SMTPuser, password);
	      email.setTLS(true);
	      email.addTo(toEmail, "title area");
	      email.setFrom(fromEmail, setfrom, charSet);
	      email.setSubject("[IT종합정보시스템] [SendEmail : " + setfrom + "] " + title); // 메일 제목
	      email.setHtmlMsg(content); // 메일 내용
	      email.send();
	      
	      return "/contact";
	    }
}
