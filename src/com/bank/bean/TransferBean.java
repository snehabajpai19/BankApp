package com.bank.bean;
import java.util.*;
public class TransferBean {
	
	private int transactionID;
	private String fromAccountNumber;
	private String toAccountNumber;
	private Date dateOfTransaction;
	private float amount;
	
	public void settransactionID(int transactionID)
	{
		this.transactionID=transactionID;
	}
	public void setfromAccountNumber(String fromAccountNumber)
	{
		this.fromAccountNumber=fromAccountNumber;
	}
	public void settoAccountNumber(String toAccountNumber)
	{
		this.toAccountNumber=toAccountNumber;
	}
	public void setdateOfTransaction(Date dateOfTransaction)
	{
		this.dateOfTransaction=dateOfTransaction;
	}
	public void setamount(float amount)
	{
		this.amount=amount;
	}
	
	public int gettransactionID()
	{
		return transactionID;
	}
	public String getfromAccountNumber()
	{
		return fromAccountNumber;
	}
	
	public String gettoAccountNumber()
	{
		return toAccountNumber;
	}
	public Date getdateOfTransaction()
	{
		return dateOfTransaction;
	}
	public float getamount()
	{
		return amount;
	}
	
	
	
	

}
