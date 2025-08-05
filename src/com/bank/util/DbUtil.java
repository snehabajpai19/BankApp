package com.bank.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbUtil {
  //1.Connection Object
	static Connection con=null;
	public static Connection getDBConnection() {
		try {
		
		//2.load driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//3.get Connection and store it
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bank","bank");
		
		

	}
		catch(ClassNotFoundException e)
		{System.out.println("Driver not found");}

		catch (SQLException e)
		{
			System.out.println(e);
		}
		return con;
}
}