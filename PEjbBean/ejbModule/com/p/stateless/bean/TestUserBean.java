package com.p.stateless.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.p.model.UserTest;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class TestUserBean
 */
@Stateless(mappedName = "TestUserBean")
@LocalBean
public class TestUserBean implements TestUserBeanRemote {


    private static final String get_By_Id = "select * from test where id = ?";
    private static String get_all_users = "select * from test";
    private static String getCount = "select count(*) from test";
	String jdbcUrl="jdbc:mysql://localhost:3306/demo?allowPublicKeyRetrieval=true&useSSL=false";
	String jdbcUsername="shenba";
	String jdbcPassword="shenbagarajan";
	String driver="com.mysql.cj.jdbc.Driver";
	private int noOfRecords;
	
    /**
     * Default constructor. 
     */
    public TestUserBean() {
        
    }

	@Override
	public void saveUser(UserTest test) {
		Connection con;
		
		try {
			
				Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(jdbcUrl , jdbcUsername, jdbcPassword);
			
			 PreparedStatement st = 
			         con.prepareStatement("insert into test(username,email) values(?,?)");
			 st.setString(1, test.getUsername());
			 st.setString(2, test.getEmail());
			 int result = st.executeUpdate();
			 
			 
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UserTest> getallusers() {
		List<UserTest> listUser=new ArrayList<>();
		try( Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
			PreparedStatement ps = con.prepareStatement(get_all_users)	){
			ResultSet rs =	ps.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				 String email= rs.getString("email");
				 listUser.add(new UserTest(username,email));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUser;
	}

	
	@Override
	public List<UserTest> findUsers(int currentPage, int recordsPerPage) {
	    List<UserTest> list = new ArrayList<>();

	    UserTest userTest;
        int start = currentPage * recordsPerPage - recordsPerPage;
        
        String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM test LIMIT "
        		+ currentPage + ", " + recordsPerPage;
        String count = "SELECT FOUND_ROWS()";
        		
        Connection con;
		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    userTest = new UserTest();
			    userTest.setUsername(rs.getString(2));
			    userTest.setEmail(rs.getString(3));
			    list.add(userTest);
			}
			rs.close();
			ps = con.prepareStatement(count);
			rs = ps.executeQuery();
			if(rs.next()) {
			      this.noOfRecords = rs.getInt(1);
   }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
        
		
        
        
		return list;
	}

	@Override
	public Integer getNumberOfRows() {

        return noOfRecords;
    }
	
	 
	}


