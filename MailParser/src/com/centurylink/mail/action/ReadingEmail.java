package com.centurylink.mail.action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

import com.centurylink.mail.datamodel.MailInfo;

public class ReadingEmail {
	String host = null;
	String mailId = null;
	String password= null; 
   	Collection<MailInfo> mailInfCollection = null;
   	
   	
	public ReadingEmail() {
		super();
	}
	
	public ReadingEmail(String host, String mailId, String password) {
		super();
		this.host = host;
		this.mailId = mailId;
		this.password = password;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<MailInfo> readMail() {
		Properties props = new Properties();
	    props.setProperty("mail.store.protocol", "imap");
		props.setProperty("mail.imaps.host", host);
		props.put("mail.imaps.port", 143);
	        
	    try {
	         Session session = Session.getInstance(props, null);
	         Store store = session.getStore();
	         store.connect(host, mailId, password);
	         Folder inbox = store.getFolder("INBOX");
	         inbox.open(Folder.READ_WRITE);
	         Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
	         mailInfCollection = new HashSet<MailInfo>();
	         MailInfo mailInfo = null;
	         for (int i = 0; i < messages.length; i++) {
	        	 Message msg = messages[i];
		         Address[] from = msg.getFrom();
		         StringBuffer stringBuffer = new StringBuffer();
		         for (Address address : from) {
		        	 stringBuffer.append(address.toString());
		         }
		         
		         if(isURLExistInString(msg.getSubject())) {
		        	 mailInfo = new MailInfo();
			         mailInfo.setSender(stringBuffer.toString());
			         mailInfo.setRecievedDate(msg.getReceivedDate());
			         System.out.println(msg.getReceivedDate());
		        	 mailInfo.setSubject(msg.getSubject());
		        	 mailInfCollection.add(mailInfo);
		         }
	         }
	         inbox.setFlags(messages, new Flags(Flags.Flag.SEEN), true);
	        } catch (Exception mex) {
	            mex.printStackTrace();
	        }
		return mailInfCollection;
	}
	
	//checks for the URL in the given String
	private boolean isURLExistInString(String s) {
		if(s != null) {
			//Separate input by spaces ( URLs don't have spaces )
	        String [] parts = s.split("\\s");
	        String inputUrl = null;
	        for(String part : parts){
	        	inputUrl = part;
	        	if (!inputUrl.contains("http://"))
	        	    inputUrl = "http://" + inputUrl;
	        	try{
	        		URL url = new URL(inputUrl);
	        		if(url != null) {
	        			return true;
	        		}
	        	}catch (MalformedURLException e) {
	        		continue;
	        	}
	        }
		}
		System.out.println(s+" : dont have any URLs/Links.");
        return false;
	}

}
