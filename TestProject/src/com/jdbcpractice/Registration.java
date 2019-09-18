package com.jdbcpractice;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Statement;

import java.sql.Connection;

public class Registration  {
	Scanner sc=new Scanner(System.in);
	String name,phnnumber,emailid,dob,passwrd;
    boolean check=true;

	public static void main(String args[]) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/practice","root","rahul@123");
		Registration obj1=new Registration();
		
		obj1.registration(con);

	}
	 public void registration(Connection con) throws SQLException {
			PreparedStatement ps=con.prepareStatement("insert into registration (name,mobile_number,email_id,dob,password) values(?,?,?,?,?)");

			System.out.println("Welcome For Registration Please Enter Your Details");
			System.out.println("Enter Your Name");
			name=sc.next();
			
			System.out.println("Enter Your Mobile Number");
			while(check) {
		    phnnumber=sc.next();
		    if(phnvalidation(phnnumber)) {
		    	break;
		    }
		    else {
		    	System.out.println("Please re-enter phnnumber");
		    	check=true ;
		    	
		    }
			}
			   while(check) {
            System.out.println("Enter Your Email Id");
            emailid =sc.next();
		    if(!isValidEmail(emailid)) {
		    	System.out.println("Email Should be @gmail.com");
		    	check=true;
		    }else {
	            try {
					if(useralreadyexist()) {
						 System.out.println("someone is already registered with this emailid please ");
					    	check=true;
                     }
					else {
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		    }
			   while(check) {
			System.out.println("Enter Your Date Of Birth dd/mm/yy");
			
			dob=sc.next();
			if(!isValiddob(dob)) {
				System.out.println("invalid date of birth");
		    	check=true;
			}
			else {
				break;
			}
			}
			while(check) {
			System.out.println("Enter Your Password");
			passwrd=sc.next();
			if(!isValidPassword(passwrd)) {
				System.out.println("password Should be alphanumeric and its length should be 5 to 10 ");
		    	check=true;
			}
			else {
				break;
			}
			
	            }
			while(check) {
			System.out.println("Please Re-Enter Your Password");
			String repasswrd=sc.next();
			if(passwrd.equals(repasswrd)) {
				System.out.println("You are successfully registered");

				break;
            }
			else {
				System.out.println("Re-Enterd Password is Wrong");
				check=false;


			}
			}
			ps.setString(1,name);
			ps.setString(2,phnnumber);
			ps.setString(3, emailid);
			ps.setString(4,dob);
			ps.setString(5,passwrd);
			ps.executeUpdate();
			System.out.println("Data Inserted successfully with");


			}
	 public boolean phnvalidation(String phnnumber) {
		    String numbervalid = "^[0123456789]\\d{9}$";

;
		    
		    	 Pattern pattern = Pattern.compile(numbervalid);
			        Matcher matcher = pattern.matcher(phnnumber);
			        return matcher.matches();		    }
		    
		    
	 public boolean isValidEmail(String emailid) {
	        String emailvalid = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	        Pattern pattern = Pattern.compile(emailvalid);
	        Matcher matcher = pattern.matcher(emailid);
	        return matcher.matches();
	        }
	 public boolean isValiddob(String dob) {
	        String dobvalid = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$" ;

	        Pattern pattern = Pattern.compile(dobvalid);
	        Matcher matcher = pattern.matcher(dob);
	        return matcher.matches();
	        }
	 private boolean isValidPassword(String passwrd) {
	        String passvalid = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{5,10})$";

	        Pattern pattern = Pattern.compile(passvalid);
	        Matcher matcher = pattern.matcher(passwrd);
	        return matcher.matches();
	    }
	 public boolean  useralreadyexist() throws Exception {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/practice","root","rahul@123");
		 String s="select * from registration where email_Id ='"+emailid+"'";

		 Statement st=con.createStatement();
		 ResultSet rs=st.executeQuery(s);
		 if(rs.next()) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	
}
