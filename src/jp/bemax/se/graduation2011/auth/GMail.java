/**
 * 
 */
package jp.bemax.se.graduation2011.auth;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Takenaka
 *
 */
public class GMail extends Thread{
	/*
	 * SMTP Settings
	 */
	/**
	 * メール送信時戻り値
	 */
	private int result = -1;

	// Host
	private static final Properties SMTP;
	static {
		SMTP = new Properties();
		SMTP.put("mail.smtp.host", "smtp.gmail.com");
		SMTP.put("mail.smtp.port", "587");
		SMTP.put("mail.smtp.auth", "true");
		SMTP.put("mail.smtp.starttls.enable", "true");
	}
	
	// Account
	private static final String SMTPID = "2011bemax@bemax.jp";
	private static final String SMTPPASS = "P@ssw0rd";
	
	/*
	 * Property
	 */
	private String mailTo = "";
	private String mailSubject = "";
	private String mailBody = "";
	
	/*
	 * default constructor
	 */
	public GMail(){
		super();
	}
	public void run(){
		
		Transport transport = null;
		
		try{
			// Session
			Session session = Session.getInstance(SMTP);
			
			// MimeMessage
			MimeMessage mimeMsg = new MimeMessage(session);
			
			// set
			mimeMsg.setFrom(new InternetAddress(SMTPID));
			mimeMsg.setSubject(this.mailSubject);
			mimeMsg.setRecipient(Message.RecipientType.TO, new InternetAddress(this.mailTo));
			mimeMsg.setContent(this.mailBody, "text/plain; charset=iso-2022-jp");
			mimeMsg.setHeader("Content-Transfer-Encoding", "7bit");
			
			// send
			transport = session.getTransport("smtp");
			transport.connect(SMTPID, SMTPPASS);
			transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
			
			result = 0;
		} catch (AddressException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			if(transport != null){
				try {
					transport.close();
				} catch (MessagingException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
	}
	

	/**
	 * @return mailTo
	 */
	public String getMailTo() {
		return mailTo;
	}

	/**
	 * @param mailTo セットする mailTo
	 */
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	/**
	 * @return mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject セットする mailSubject
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * @return mailBody
	 */
	public String getMailBody() {
		return mailBody;
	}

	/**
	 * @param mailBody セットする mailBody
	 */
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	/**
	 * @return result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result セットする result
	 */
	public void setResult(int result) {
		this.result = result;
	}
}
