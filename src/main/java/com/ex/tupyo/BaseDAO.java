package com.ex.tupyo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {

	protected DataSource dataSource;
	private Connection connection;
	Context context;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public BaseDAO() {
		try{
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/oracle");
			connection = dataSource.getConnection();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<TupyoDTO> titleView(){
		// home.jsp에서 타이틀보여주기
		ArrayList<TupyoDTO> dtos = new ArrayList<TupyoDTO>();
		
		
		try{
			
			String query = "select * from chw_tupyo";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("row : "+resultSet.getRow());
			while(resultSet.next()){
			
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				int agree = resultSet.getInt("agree");
				int disagree = resultSet.getInt("disagree");
			
				TupyoDTO dto = new TupyoDTO(id,title, agree, disagree);
				
				dtos.add(dto);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
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
		return dtos;
		
	}
	public ArrayList<TupyoDTO> result(){
		// result.jsp에서 결과 보여주기
		ArrayList<TupyoDTO> dtos = new ArrayList<TupyoDTO>();
		try{
			
			String query = "select * from chw_tupyo";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
				
		while(resultSet.next()){
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			int agree = resultSet.getInt("agree");
			int disagree = resultSet.getInt("disagree");
			
			TupyoDTO dto = new TupyoDTO(id, title, agree, disagree);
			dtos.add(dto);
			
		}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			try{
				
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
		return dtos;
	}
	public void upHit(String result, String id){
		
		String query ="";
		try{
		
			if(result.equals("agree"))
			{
				System.out.println("agree");
				query = "update chw_tupyo set agree = agree + 1 where id = ?";
			}
			else
			{
				System.out.println("disagree");
				query = "update chw_tupyo set disagree = disagree + 1 where id = ?";
			}
			 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			
			int rn = preparedStatement.executeUpdate();	
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			try{
				if(preparedStatement !=null){
					preparedStatement.close();
				}
				if(connection !=null){
					connection.close();
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
