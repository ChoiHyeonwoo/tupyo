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
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<TupyoDTO> titleView(){
		// home.jsp에서 타이틀보여주기
		ArrayList<TupyoDTO> dtos = new ArrayList<TupyoDTO>();
		
		
		try{
			connection = dataSource.getConnection();
			String query = "select * from chw_tupyo";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
			
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				int agree = resultSet.getInt("agree");
				int disagree = resultSet.getInt("disagree");
				String writer = resultSet.getString("writer");
			
				TupyoDTO dto = new TupyoDTO(id,title, agree, disagree, writer);
				
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
			connection = dataSource.getConnection();
			String query = "select * from chw_tupyo";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
				
		while(resultSet.next()){
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			int agree = resultSet.getInt("agree");
			int disagree = resultSet.getInt("disagree");
			String writer = resultSet.getString("writer");
			
			TupyoDTO dto = new TupyoDTO(id, title, agree, disagree, writer);
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
	public String had_tupyo(String t_id, String t_member){
		String result = "";
		
		try{
			connection = dataSource.getConnection();			
			String query = "select * from chw_tupyo_recode where t_id = ? and t_member =?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			preparedStatement.setString(2, t_member);
			
			resultSet = preparedStatement.executeQuery();
				
		if (resultSet.next()){
			result = "disavailable";			
		}else{
			result = "available";
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
		return result;
	}
	public void tupyo_log(String t_id, String t_member, String t_content){
		try{
			connection = dataSource.getConnection();
			String query = "insert into chw_tupyo_recode (pk_tid, t_id, t_member, t_content) values "
					+ "(chw_tupyo_recode_seq.nextval, ?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			preparedStatement.setString(2, t_member);
			preparedStatement.setString(3, t_content);
			
			int rn =  preparedStatement.executeUpdate();

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
	}
	public void upHit(String result, String id){
		
		String query ="";
		try{
			connection = dataSource.getConnection();
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
	public void reg_poll(String title, String writer){
			
			String query ="";
			try{
				connection = dataSource.getConnection();
				query = "insert into chw_tupyo (id, title, agree, disagree, writer) values (chw_tupyo_seq.nextval, ?, ?, ?, ?)";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, title);
				preparedStatement.setInt(2, 0);
				preparedStatement.setInt(3, 0);
				preparedStatement.setString(4, writer);
				
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
