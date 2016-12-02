package com.antflo.ws.rest.Messenger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import java.util.Date;

@Entity
public class Comment {

	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	 @Column
	private String message;
	 @Column
	private Date created;
	 @Column
	private String author;
	 @Column
	 private int messageId;
	
	public Comment (){
		
	}

	

	public Comment(int id, String message, Date created, String author, int messageId) {
		this.id = id;
		this.message = message;
		this.created = created;
		this.author = author;
		this.messageId = messageId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	
	
	
}
