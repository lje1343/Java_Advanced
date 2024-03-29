package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.NamingException;

import jdbc.UserDTO;
import util.ConnectionPool;

public class UserDAO {
	
	
	public UserDTO getUser(String user_no) 
            throws NamingException, SQLException{
            
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            
            try {
               String sql = "SELECT * FROM user WHERE user_no = ?";
               
               conn = ConnectionPool.get();
               pstmt = conn.prepareStatement(sql);
                  pstmt.setString(1, user_no);
               rs = pstmt.executeQuery();
               
               rs.next();
               
               String no = rs.getString(1);
               String user_name = rs.getString(2);
               String user_pw = rs.getString(3);
               String user_birth = rs.getString(4);
               String user_zipcode = rs.getString(5);
               String user_addr = rs.getString(6);
               String user_addrdetail = rs.getString(7);
               String user_gender = rs.getString(8);
               String user_email = rs.getString(9);
               String user_phone = rs.getString(10);
               String user_admin = rs.getString(11);
               String user_kakao = rs.getString(12);
               String user_auth = rs.getString(13);
               String user_status = rs.getString(14);
               
               UserDTO user = new UserDTO(no,
                                    user_name,
                                    user_pw,
                                    user_birth,
                                    user_zipcode,
                                    user_addr,
                                    user_addrdetail,
                                    user_gender,
                                    user_email,
                                    user_phone,
                                    user_admin,
                                    user_kakao,
                                    user_auth,
                                    user_status
                                    );
               
               return user;
               
            }finally {
               if(rs != null) rs.close();
               if(pstmt != null) pstmt.close();
               if(conn != null) conn.close();
            }
            
         }
	
	
	/* email로 user_no 받아오는 메서드(useradd.jsp) */
	public String getUserNo(String user_email) 
			throws NamingException, SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT user_no FROM user WHERE user_email = ?";
			
			conn = ConnectionPool.get();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_email);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			String user_no = rs.getString("user_no");
			
			
			return user_no;
			
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
	}
	
	
	
	// useradd.jsp
	/* user_no, user_admin, user_kakao, user_auth, user_status */
	
	public int insert(String user_name, String user_pw, String user_birth, String user_zipcode, 
			String user_addr, String user_addrdetail, String user_gender, String user_email, String user_phone)
	throws NamingException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO user VALUES (NULL,?,?,?,?,?,?,?,?,?,NULL,NULL,NULL,NULL)";
			conn = ConnectionPool.get();
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_name);
				pstmt.setString(2, user_pw);
				pstmt.setString(3, user_birth);
				pstmt.setString(4, user_zipcode);
				pstmt.setString(5, user_addr);
				pstmt.setString(6, user_addrdetail);
				pstmt.setString(7, user_gender);
				pstmt.setString(8, user_email);
				pstmt.setString(9, user_phone);
			int result = pstmt.executeUpdate();
			
			return result;			
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	
	// emailCheckPro.jsp (이메일중복체크)
	public int duplecateID(String user_email){ 
		int cnt=0; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{ 
			//아이디 중복 확인 
			
			String sql = "SELECT count(user_email) as cnt FROM user WHERE user_email = ?";
			conn = ConnectionPool.get();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_email); 
			rs = pstmt.executeQuery(); 
			
			if(rs.next()){ 
				cnt=rs.getInt("cnt"); 
				} 
			}catch(Exception e){ 
				System.out.println("이메일 중복 확인 실패 : " + e); 
		}
	
		return cnt;
	}
	
	/*
	public int userinsert(String user_name, String user_pw, String user_birth, String user_zipcode, 
			String user_addr, String user_addrdetail, String user_gender, String user_email, String user_phone,
			String trainer_title, String trainer_content, String trainer_addr, ArrayList<String> arr2)
	throws NamingException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = null;
		
		if()
	}
	
	public int userinsert(String user_name, String user_pw, String user_birth, String user_zipcode, 
			String user_addr, String user_addrdetail, String user_gender, String user_email, String user_phone,
			String gym_name, String gym_content, String gym_addr, String gym_salary, ArrayList<String> arr)
	throws NamingException, SQLException{
		
	}
	*/
	
}
