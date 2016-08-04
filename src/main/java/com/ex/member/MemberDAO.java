package com.ex.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ex.tupyo.BaseDAO;

public class MemberDAO extends BaseDAO{
	
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public MemberDAO(){
		super();
	}
	// register
	public String register(String id, String password, String name){
		String result = "";
		try{
			
			//connection
			connection = super.dataSource.getConnection();
			String query = "insert into chw_member () values (?,?,?,Y, sysdate, , chw_member_seq.nextval)";
			//preparedStatement
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			
			int rn = preparedStatement.executeUpdate();
			
			result = "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				// connection dispose
				if(connection!=null)
					connection.close();
				if(preparedStatement!=null)
					preparedStatement.close();
				if(resultSet!=null)
					resultSet.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return result;
		
	}

}
