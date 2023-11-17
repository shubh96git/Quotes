package com.realmaverick.entities;

import java.util.Date;

public class Quotes {

	//fields of POJO
	private int qId;
	private String quote;
	private String author;
	private int userId;
	private java.util.Date createdAt;
	

	//ctors
	public Quotes() {
	
	}
	
	public Quotes(int qId, String quote, String author, int userId, Date createdAt) {
		this.qId = qId;
		this.quote = quote;
		this.author = author;
		this.userId = userId;
		this.createdAt = createdAt;
	}

	//getters and setters
	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public java.util.Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}

	//tostring
	@Override
	public String toString() {
		return "Quotes [qId=" + qId + ", quote=" + quote + ", author=" + author + ", userId=" + userId + ", createdAt="
				+ createdAt + "]";
	}
	
	
	
	
}
