package com.bank.service;

import com.bank.util.DbUtil;
import com.bank.util.InsufficientFundsException;
import com.bank.bean.TransferBean;
import com.bank.dao.BankDAO;


public class BankMain {
	public static void main(String [] args)
	{
		BankMain bankMain=new BankMain();
		System.out.println(bankMain.checkBalance("1234567890"));
		TransferBean beanie=new TransferBean();
		beanie.setamount(1000);
		beanie.setfromAccountNumber("1234567890");
		beanie.settoAccountNumber("1234567891");
		System.out.println(bankMain.transfer(beanie));
		BankDAO obj=new BankDAO();
		if(obj.transferMoney(beanie))
		System.out.println("Money Transferred");
		
		
	}
	public String checkBalance(String accountNumber) {
		BankDAO obj=new BankDAO();
		boolean bool=obj.validateAccount(accountNumber);
		if(bool) {
			float balance=obj.findBalance(accountNumber);
			if(balance!=-1)
			{
				return "BALANCE IS:"+balance;
			}
			else
				return  "ACCOUNT NUMBER INVALID";
		}
		else
			return  "ACCOUNT NUMBER INVALID";
	}

	public String transfer(TransferBean transferBean)
	{
		BankDAO obj=new BankDAO();
		
		if(transferBean==null)
			return "INVALID";
		else if(!(obj.validateAccount(transferBean.getfromAccountNumber()) && obj.validateAccount(transferBean.gettoAccountNumber())))
			return "INVALID ACCOUNT";
		
		else if(obj.findBalance(transferBean.getfromAccountNumber())<transferBean.getamount())
		{
			try {
			throw new InsufficientFundsException();
			}
			catch(InsufficientFundsException e)
			{
				System.out.println(e);
			}
		}
		else
		{
			float bal1=obj.findBalance(transferBean.getfromAccountNumber());
			bal1=bal1-transferBean.getamount();
			float bal2=obj.findBalance(transferBean.gettoAccountNumber());
			bal2=bal2+transferBean.getamount();
			
			if(obj.updateBalance(transferBean.getfromAccountNumber(), bal1) && obj.updateBalance(transferBean.gettoAccountNumber(), bal2) )
				return "SUCCESS";
			
	}
		return "INSUFFICIENT FUNDS";
	}
}
