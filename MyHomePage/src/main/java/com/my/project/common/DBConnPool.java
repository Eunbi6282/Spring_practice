package com.my.project.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnPool {
	
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs; 
	
	//ï¿½âº» ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ 
	public DBConnPool() {
		
		
       try {
            // JDBC ï¿½ï¿½ï¿½ï¿½Ì¹ï¿? ï¿½Îµï¿½
            Class.forName("oracle.jdbc.OracleDriver");

            // DBï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
            String url = "jdbc:oracle:thin:@localhost:1521:xe";  
            String id = "SHOPPING";
            String pwd = "1234"; 
            con = DriverManager.getConnection(url, id, pwd); 

            System.out.println("DB ?—°ê²? ?„±ê³?(ê¸°ë³¸ ?ƒ?„±?)");
        }
        catch (Exception e) {            
            e.printStackTrace();
            System.out.println("db?˜¤ë¥˜ì…?‹ˆ?‹¤.");
        }
	}
	
	public void close() {
		try {
			if (rs != null)  rs.close(); 
			if (psmt != null) psmt.close();
			if(stmt != null) stmt.close();
			if (con != null) con.close();
			System.out.println("DB ì»¤ë„¥?…˜ ?? ??› ë°˜ë‚© (?„±ê³?) ");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB ì»¤ë„¥?…˜ ?? ??› ë°˜ë‚© (?‹¤?Œ¨) ");
		}
		
	}

}
