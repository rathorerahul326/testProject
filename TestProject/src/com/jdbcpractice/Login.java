package com.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
public static void main(String args[]) throws ClassNotFoundException, Exception {
	System.out.println("Welcome to Login Page");
	Scanner sc=new Scanner(System.in);
	String emailid,passwrd,fetchpasswrd = null;
	boolean check=true;
	System.out.println("Enter Your Email Id");
    emailid =sc.next();
    while(check) {
    System.out.println("Enter Your Password");
	passwrd=sc.next();
	
		
	    Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/practice","root","rahul@123");
		
		Statement s=con.createStatement();
		
		ResultSet rs=s.executeQuery("select * from registration where email_Id='"+emailid+"'");
		if(rs.first()) {
				 fetchpasswrd=rs.getString(5);
		}
				if(passwrd.equals(fetchpasswrd))
				{
					System.out.println("You are successfully Login");
					break;
				}
				else {
					System.out.println("Your password is wrong ");
					check=true;

				}
				con.close();

}
}
}
