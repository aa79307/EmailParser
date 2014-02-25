package com.centurylink.mail.datamodel;

import java.util.Date;

public class MailInfo {
	String sender;
	String subject;
	Date recievedDate;
	
	
	public MailInfo() {
		super();
	}

	public MailInfo(String sender, String subject,
			Date recievedDate) {
		super();
		this.sender = sender;
		this.subject = subject;
		this.recievedDate = recievedDate;
	}

	@Override
	public String toString() {
		return "MailInfo [sender=" + sender + ", subject=" + subject
				+ ", recievedDate=" + recievedDate + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((recievedDate == null) ? 0 : recievedDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailInfo other = (MailInfo) obj;
		if (recievedDate == null) {
			if (other.recievedDate != null)
				return false;
		} else if (!recievedDate.equals(other.recievedDate))
			return false;
		return true;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}
	
}
