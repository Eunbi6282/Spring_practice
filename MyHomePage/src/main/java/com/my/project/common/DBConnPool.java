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
	
	//�⺻ ������ 
	public DBConnPool() {
		
		
       try {
            // JDBC ����̹�? �ε�
            Class.forName("oracle.jdbc.OracleDriver");

            // DB�� ����
            String url = "jdbc:oracle:thin:@localhost:1521:xe";  
            String id = "SHOPPING";
            String pwd = "1234"; 
            con = DriverManager.getConnection(url, id, pwd); 

            System.out.println("DB ?���? ?���?(기본 ?��?��?��)");
        }
        catch (Exception e) {            
            e.printStackTrace();
            System.out.println("db?��류입?��?��.");
        }
	}
	
	public void close() {
		try {
			if (rs != null)  rs.close(); 
			if (psmt != null) psmt.close();
			if(stmt != null) stmt.close();
			if (con != null) con.close();
			System.out.println("DB 커넥?�� ?? ?��?�� 반납 (?���?) ");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 커넥?�� ?? ?��?�� 반납 (?��?��) ");
		}
		
	}

}
