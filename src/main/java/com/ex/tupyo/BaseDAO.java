package com.ex.tupyo;

import java.sql.Connection;
import java.util.Date;
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
				String is_duplicated = resultSet.getString("is_duplicated");
				Date reg_date = resultSet.getDate("reg_date");
				
				
				TupyoDTO dto = new TupyoDTO(id,title, agree, disagree, writer, is_duplicated, reg_date);
				
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
			String is_duplicated = resultSet.getString("is_duplicated");
			Date reg_date = resultSet.getDate("reg_date");
			
			TupyoDTO dto = new TupyoDTO(id, title, agree, disagree, writer, is_duplicated, reg_date);
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
			String query = "insert into chw_tupyo_recode (pk_tid, t_id, t_member, t_content, t_date) values "
					+ "(chw_tupyo_recode_seq.nextval, ?,?,?, sysdate)";
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
	public int[] getTupyoResult(String t_id){
		String query = "";
		int result_arr[] = new int[2];
		int agree = 0;
		int disagree = 0;
		try{
			connection = dataSource.getConnection();

			System.out.println("agree");
			query = "select * from chw_tupyo_recode where t_id = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			ResultSet resultSet = preparedStatement.executeQuery();	
			
			while(resultSet.next()){
				if(resultSet.getString("t_content").equals("agree")){
					agree++;
				}
				else{
					disagree++;
				}
				
			}
			result_arr[0] = agree;
			result_arr[1] = disagree;
			
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
		return result_arr;
		
	}
	
	public void upHit(String result, String t_id, int agree, int disagree){
		
		String query ="";
		try{
			connection = dataSource.getConnection();
			query = "update chw_tupyo set agree = ?, disagree = ? where id = ?";
					 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, agree);
			preparedStatement.setInt(2, disagree);
			preparedStatement.setInt(3, Integer.parseInt(t_id));
			
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
	public String is_duplicated(String t_id){
		String result = "";
		
		try{
			connection = dataSource.getConnection();
			String query = "select * from chw_tupyo where id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			resultSet = preparedStatement.executeQuery();
				
		if(resultSet.next()){
			String is_duplicated = resultSet.getString("is_duplicated");
			
			result = is_duplicated;
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
			
	public void reg_poll(String title, String writer, String is_duplicated){
			
			String query ="";
			try{
				connection = dataSource.getConnection();
				query = "insert into chw_tupyo (id, title, agree, disagree, writer, is_duplicated, reg_date) values (chw_tupyo_seq.nextval, ?, ?, ?, ?, ?, sysdate)";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, title);
				preparedStatement.setInt(2, 0);
				preparedStatement.setInt(3, 0);
				preparedStatement.setString(4, writer);
				preparedStatement.setString(5, is_duplicated);
				
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
