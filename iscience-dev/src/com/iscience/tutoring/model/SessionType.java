package com.iscience.tutoring.model;

public class SessionType {
	private int price;
	private String subject;
	
	public SessionType(String subject)
	{
		this.subject = subject;
	}
	
	public int getPrice() 
	{
		return price;
	}
	public void setPrice(int price) 
	{
		this.price = price;
	}
	public String getSubject() 
	{
		return subject;
	}
	@Override
	public String toString() 
	{
		return "SessionDetails [price=" + price + ", subject=" + subject + "]";
	}
}
