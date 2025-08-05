package com.bank.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bank.bean.TransferBean;
import com.bank.util.DbUtil;

public class BankDAO {

	public int generateSequenceNumber()
	{
		Connection con=DbUtil.getDBConnection();
		int myId=0;
		String sql="Select transactionId_seq.nextval from dual";
		try
		{
		PreparedStatement pst=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			myId=rs.getInt(1);
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return myId;
	}
	
	public boolean validateAccount(String accountNumber)
	{
		Connection con=DbUtil.getDBConnection();
		String sql="Select ACCOUNT_NUMBER from ACCOUNT_TBL";
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			int flag=0;
			while(rs.next())
			{
				if(rs.getString(1).equals(accountNumber))
					flag=1;
			}
			if(flag==1)
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public float findBalance(String accountNumber)
	{
		if(validateAccount(accountNumber))
		{
		Connection con=DbUtil.getDBConnection();
		float balance=0.0f;
		
		String sql="Select balance from ACCOUNT_TBL where ACCOUNT_NUMBER="+"'"+accountNumber+"'";
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				balance=rs.getFloat(1);
			}
			return balance;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		}
		return -1;
	}
	public boolean transferMoney(TransferBean transferBean)
	{
		Connection con=DbUtil.getDBConnection();
		String sql="insert into TRANSFER_TBL values(?,?,?,?)";
		try
		{
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1,generateSequenceNumber());
		pst.setString(2,transferBean.getfromAccountNumber());
		pst.setString(3, transferBean.gettoAccountNumber());
		java.util.Date date=new java.util.Date();
		long t=date.getTime();
		java.sql.Date sqldate=new java.sql.Date(t);
		pst.setDate(4, sqldate);
		if(pst.executeQuery()!=null)
		{return true;}
		else
		return false;	
		

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean updateBalance(String accountNumber, float newBalance)
	{
		Connection con=DbUtil.getDBConnection();
		String sql="update ACCOUNT_TBL set balance="+newBalance+"where ACCOUNT_NUMBER="+"'"+accountNumber+"'";
		try
		{
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			if(rs!=null)
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
