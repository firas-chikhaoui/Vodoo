package com.vodoo.vodooapiadmin.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.vodoo.vodooapiadmin.dto.Enum.Message;
import com.vodoo.vodooapiadmin.service.IMailService;
import com.vodoo.vodooapiadmin.dto.SendmailDto;
import com.vodoo.vodooapiadmin.dto.ModuleResponse;
import com.vodoo.vodooapipersistance.model.Utilisateur;

@Service
public class MailServiceImpl implements IMailService {

	 @Autowired
	 public JavaMailSender emailSender;
	  

	    private String url_frontend="http://54.37.212.235:4200/auth/resetPassword";

	 @Override
	    public ModuleResponse sendSimpleMessage(SendmailDto sendmailDto) {
	        try {
//	          
	            MimeMessage message = emailSender.createMimeMessage();

	            boolean multipart = true;

	            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");


	            message.setContent(sendmailDto.getText(), "text/html");

	            helper.setTo(sendmailDto.getSendto());

	            helper.setSubject(sendmailDto.getSubject());


	            this.emailSender.send(message);
	            return new ModuleResponse(Message.SUCCESS_SEND.code, Message.SUCCESS_SEND.label, null);

	        }catch (Exception e){
	            return new ModuleResponse(Message.ERRUER_SEND.code, e.getMessage(), null);

	        }

	    }
	 
	 
	 @Override
	    public ModuleResponse sendSimpleMessageForResetPassword(Utilisateur utilisateur,String token) {
	        try{
	            String htmltext= "<html>"+
	                    "<head>"+
	                    "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n" +
	                    "\n" +
	                    "<!-- Optional theme -->\n" +
	                    "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">\n" +
	                    "\n" +
	                    "<!-- Latest compiled and minified JavaScript -->\n" +
	                    "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>"+
	                    "</head>"+
	                    "<body>"+
	                    "<div class='row'> <h1>Cher "+utilisateur.getPrenom()+"</h1></div>"+
	                    "<div class='row'>  </div>"+
	                    "<div class='row'> Vous avez rÃ©cemment demandÃ© Ã  rÃ©initialiser votre mot de passe pour <br> votre compte.  Utilisez le bouton ci-dessous pour le rÃ©initialiser.<strong> Cette <br> rÃ©initialisation du mot de passe n'est valable que pour les <br> prochaines 24 heures. </strong>"+
	                    "<div class='row'> <a href='"+url_frontend+"password/"+token+"' class='btn btn-success'>RÃ©initialisez votre mot de passe</a>\n </div>"+
	                    "<div class='row'> Si vous n'avez pas demandÃ© de rÃ©initialisation de mot de passe, <br> veuillez ignorer cet email ou contacter le support technique <br> sur support@vegagroup.com.tn si vous avez des questions. </div>"+
	                    "<br>"+
	                    "<div class='row'></div>"+
	                    "<hr>"+
	                    "<br>"+
	                    "<div class='row'> Si vous rencontrez des problÃ¨mes avec le bouton ci-dessus, copiez et collez lâ€™URL ci-dessous dans votre navigateur Web. </div>"+
	                    "<br>"+
	                    "<div class='row'> <a href='"+url_frontend+""+token+"' class='btn btn-success'>"+url_frontend+""+token+"</a> </div>"+
	                    " </body>"+
	                    "</html>";
	            SendmailDto sendmailDto=new SendmailDto();
	            sendmailDto.setText(htmltext);
	            sendmailDto.setSubject("Reset Password");
	            sendmailDto.setSendto(utilisateur.getEmail());
	            return sendSimpleMessage(sendmailDto);
	        }catch (Exception e){
	            return new ModuleResponse(Message.ERRUER_SEND.code, e.getMessage(), null);
	        }

	    }

}
