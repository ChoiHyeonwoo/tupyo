package com.ex.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ex.member.MemberDAO;
import com.ex.tupyo.BaseDAO;

public class AdminDAO extends BaseDAO {

	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	public AdminDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public String admin_login(String logid, String logpw){
		String result = "";
		String query = "select * from chw_member where not grade = ? and id = ?";
		MemberDAO mdao = new MemberDAO();
		try{
			connection = super.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 9);
			preparedStatement.setString(2, logid);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				if(logpw.equals(resultSet.getString("password"))){
					
					
				}else{
					mdao.update_log(logid, "admin_login_fail");
				}
						
				
			}else{
				mdao.update_log(logid, "admin_login_fail");
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}
