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
	public void register(String id, String password, String name){
		
		try{
			
			//connection
			connection = super.dataSource.getConnection();
			String query = "";
			//preparedStatement
			preparedStatement = connection.prepareStatement(query);
			
			
			
			
			//sql send
			
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
		
	}

}
