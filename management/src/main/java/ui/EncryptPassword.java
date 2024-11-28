package ui;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EncryptPassword {
	 
	 
	 public static String encryptPassword(String input){
	     String encPass=null;
	     if(input==null) return null;
	     
	     try{
	         MessageDigest digest=MessageDigest.getInstance("MD5");
	         digest.update(input.getBytes(),0,input.length());
	         encPass=new BigInteger(1,digest.digest()).toString(16);
	     }
	     catch(Exception e){
	         e.printStackTrace();
	     }
	     return encPass;
	     
	 }
	 
	 public static boolean checkLogin(String username, String password) {
		 Connection con = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         // Connect to Oracle database
         try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        String query = "SELECT * FROM user1 WHERE username='" + username + "' AND password='" + password + "' FETCH FIRST 1 ROWS ONLY";
	        System.out.println(query);
	        
	        try {
	            rs = stmt.executeQuery(query);
	            System.out.println("Executed the query : "+ query);
	            if (rs.next()) {
	                return true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	 public static void main(String[] args) {
	     System.out.println(encryptPassword("1234"));
	 }
}