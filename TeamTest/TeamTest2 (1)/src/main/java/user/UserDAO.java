package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDAO {
	//dao:데이터베이스 접근 객체의 약자로
	//실질적으로 db에서 회원정보 불러오거나 db에 회원정보를 넣을때
	private Connection conn; //connection db에 접근하게 해주는 객체
	private PreparedStatement pstmt;
	private ResultSet rs;
	//Oracle에 접속 해주는 부분
	public UserDAO() {//생성자 실행될때마다 자동으로 db연결이 이루어질수 있도록함
		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "hr3";
			String dbPassword = "1234";
			
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			System.out.println("DB에 연결 되었습니다.\n");
			
		}catch(ClassNotFoundException e) {
			System.out.println("DB 드라이버 로딩 실패 :" +e.toString());
		}catch(SQLException sqle) {
			System.out.println("DB 접속실패 :"+sqle.toString());
		}catch(Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}
	 public int login(String userID, String userPassword) {
		 String SQL = "SELECT userPassword FROM USER01 WHERE userID = ?";
		 
		 try {
			 pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1, userID);
			 //rs:result set에 결과보관
			 rs = pstmt.executeQuery();
			 //결과가 존재한다면 실행
			 if(rs.next()) {
				 //패스워드 일치한다면 실행
				 if(rs.getString(1).equals(userPassword)) {
					 return 1;//로그인성공
				 }else
					 return 0;//비번 불일치
			 }return -1;//아이디 없음
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }return -2;//데이터베이스 오류를 의미
	 }
	 
	 public int join(User user) {
		 String SQL = "INSERT INTO USER01 VALUES (?,?,?,?,?,?)";
		 try {
			 pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1, user.getUserID());
			 pstmt.setString(2, user.getUserPassword());
			 pstmt.setString(3, user.getUserName());
			 pstmt.setString(4, user.getUserEmail());
			 pstmt.setString(5, user.getUserAddress());
			 pstmt.setString(6, user.getUserRole());
			 return pstmt.executeUpdate();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return -1;//DB오류
	 }
	 
	 //역할 찾기
	 public String findRole(String userID) {
		 String SQL = "SELECT userRole FROM user01 where userID = ?";
		 String ab = "";
		 try {
			 pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1, userID);
			 rs=pstmt.executeQuery();
			 
			 if (rs.next()) {
			 ab = rs.getString(1);
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return ab;
	 }
	 
	 public int updateUser(String userName, String userEmail, String userAddress, String userRole, String userId) {
		 String SQL = "UPDATE user01 SET userName = ?, userEmail = ?, userAddress = ?, userRole = ? where userId = ?";
		 
		 try {
			 pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1, userName);
			 pstmt.setString(2, userEmail);
			 pstmt.setString(3, userAddress);
			 pstmt.setString(4, userRole);
			 pstmt.setString(5, userId);
			 
			 return pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("회원수정 오류");
		 }
		 return -1;
	 }
	 
	 //리스트 만들기(모든 출력을 위한 리스트)
	 public ArrayList<User> getUList(){
		 String SQL = "SELECT * FROM USER01";
		 ArrayList<User> ulist = new ArrayList<User>();
		 try {
			 pstmt = conn.prepareStatement(SQL);
			 rs = pstmt.executeQuery(SQL);
			 
			 while(rs.next()) {
				 User user = new User();
				 user.setUserID(rs.getString(1));
				 user.setUserPassword(rs.getString(2));
				 user.setUserName(rs.getString(3));
				 user.setUserEmail(rs.getString(4));
				 user.setUserAddress(rs.getString(5));
				 user.setUserRole(rs.getString(6));
				 
				 ulist.add(user);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("유저리스트 오류");
		 }
		return ulist;
	 }
	 
	 //리스트 만들기(모든 출력을 위한 리스트)
	 public ArrayList<User> SUserInfoList(String userId){
		 String SQL = "SELECT * FROM USER01 where userID = ?";
		 ArrayList<User> ulist = new ArrayList<User>();
		 try {
			 pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1, userId);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 User user = new User();
				 user.setUserID(rs.getString(1));
				 user.setUserPassword(rs.getString(2));
				 user.setUserName(rs.getString(3));
				 user.setUserEmail(rs.getString(4));
				 user.setUserAddress(rs.getString(5));
				 user.setUserRole(rs.getString(6));
				 
				 ulist.add(user);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println("유저리스트 오류");
		 }
		return ulist;
	 }
	 
	 public User getUser(String userID) {
			String SQL = "SELECT * FROM user01 WHERE userID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					User user = new User();
					user.setUserID(rs.getString(1));
					user.setUserPassword(rs.getString(2));
					user.setUserName(rs.getString(3));
					user.setUserEmail(rs.getString(4));
					user.setUserAddress(rs.getString(5));
					user.setUserRole(rs.getString(6));

					return user;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
 }