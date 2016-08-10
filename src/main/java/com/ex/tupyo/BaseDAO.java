package com.ex.tupyo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
	public ArrayList<TupyoItemDTO> tupyo_detail_view(String tupyo_id){
		ArrayList<TupyoItemDTO> tidtos = new ArrayList<TupyoItemDTO>();

		try{
			connection = dataSource.getConnection();
			
			String query = "select * from chw_tupyo_items where t_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(tupyo_id));
			
			resultSet = preparedStatement.executeQuery();	
			
			while(resultSet.next()){
				int pk_id = resultSet.getInt("pk_id");
				int t_id = resultSet.getInt("t_id");
				String t_item_content = resultSet.getString("t_item_content");
				int t_item_selected = resultSet.getInt("t_item_selected");
				String t_title = resultSet.getString("t_title");
				
				TupyoItemDTO tidto = new TupyoItemDTO(pk_id, t_id, t_item_content, t_item_selected, t_title);
				
				tidtos.add(tidto);
			}
							
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(preparedStatement !=null){
					preparedStatement.close();
					
				}
				if(connection !=null){
					
					connection.close();
					
				}	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		return tidtos;
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
				String writer = resultSet.getString("writer");
				String is_duplicated = resultSet.getString("is_duplicated");
				Date reg_date = resultSet.getDate("reg_date");
				int item_number = resultSet.getInt("item_number");
				String is_multi_check = resultSet.getString("is_multi_check");
				
				TupyoDTO dto = new TupyoDTO(id, title,  writer, is_duplicated, reg_date, item_number, is_multi_check);
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
	public ArrayList<TupyoDTO> search_tupyo(String option, String content){
		// home.jsp에서 타이틀보여주기
		ArrayList<TupyoDTO> dtos = new ArrayList<TupyoDTO>();
		

		try{
			connection = dataSource.getConnection();
			String query = "select * from chw_tupyo where 1=1";
				if(option.equals("title"))
				{
					query +=  "and title = ?";
				}
			
			
			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, option);
			preparedStatement.setString(1, content);
			
			
			resultSet = preparedStatement.executeQuery();
			System.out.println(option + "  "  + content);
			while(resultSet.next()){
			
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String writer = resultSet.getString("writer");
				String is_duplicated = resultSet.getString("is_duplicated");
				Date reg_date = resultSet.getDate("reg_date");
				int item_number = resultSet.getInt("item_number");
				String is_multi_check = resultSet.getString("is_multi_check");
				
				TupyoDTO dto = new TupyoDTO(id, title,  writer, is_duplicated, reg_date, item_number, is_multi_check);
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
	public TupyoDTO result(String t_id){
		// result.jsp에서 결과 보여주기
		TupyoDTO dto = null;
		try{
			connection = dataSource.getConnection();
			String query = "select * from chw_tupyo where id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			resultSet = preparedStatement.executeQuery();
				
		if (resultSet.next()){
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			String writer = resultSet.getString("writer");
			String is_duplicated = resultSet.getString("is_duplicated");
			Date reg_date = resultSet.getDate("reg_date");
			int item_number = resultSet.getInt("item_number");
			String is_multi_check = resultSet.getString("is_multi_check");
			
			dto = new TupyoDTO(id, title,  writer, is_duplicated, reg_date, item_number, is_multi_check);
			
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
		return dto;
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
	public ArrayList<TupyoRecodeDTO> tupyo_log_view(String t_id, String t_member){
		ArrayList<TupyoRecodeDTO> trdtos = new ArrayList<TupyoRecodeDTO>();
		
		try{
			connection = dataSource.getConnection();			
			String query = "select * from chw_tupyo_recode where t_id = ? and t_member =?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			preparedStatement.setString(2, t_member);
			
			resultSet = preparedStatement.executeQuery();
				
		while (resultSet.next()){
			int pk_tid = resultSet.getInt("pk_tid");
			int tupyo_id = resultSet.getInt("t_id");
			String tupyo_member = resultSet.getString("t_member");
			String t_content = resultSet.getString("t_content");
			Date t_date = resultSet.getDate("t_date");
			
			TupyoRecodeDTO trdto = new TupyoRecodeDTO(pk_tid, tupyo_id, tupyo_member, t_content, t_date);
			
			trdtos.add(trdto);
			
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
		return trdtos;
	}
	public void tupyo_log(String t_id, String t_member, String t_content){
		try{
			
			connection = dataSource.getConnection();
			connection.setAutoCommit(false); // 트랜잭션을 사용하기 위해서 AutoCommit을 정지한다 
			String query = "insert into chw_tupyo_recode (pk_tid, t_id, t_member, t_content, t_date) values "
					+ "(chw_tupyo_recode_seq.nextval, ?,?,?, sysdate)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			preparedStatement.setString(2, t_member);
			preparedStatement.setString(3, t_content);
			
			int rn =  preparedStatement.executeUpdate();
			if(rn < 1 ){
				connection.rollback();
			}
			connection.commit(); 
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			
			try{
				
				if(connection!=null){
					connection.setAutoCommit(true); //트랜잭션 처리를 기본상태로 되돌린다.
					connection.close();
				}
				
				if(preparedStatement!=null)
					preparedStatement.close();
				if(resultSet!=null)
					resultSet.close();
				 
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
			
		}
	}
	
	public void tupyo_log_multi(String t_id, String t_member, String[] t_content){
		try{
			
			connection = dataSource.getConnection();
			connection.setAutoCommit(false); // 트랜잭션을 사용하기 위해서 AutoCommit을 정지한다 
			
			for(int i = 0; i < t_content.length; i++){
				String query = "insert into chw_tupyo_recode (pk_tid, t_id, t_member, t_content, t_date) values "
						+ "(chw_tupyo_recode_seq.nextval, ?,?,?, sysdate)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(t_id));
				preparedStatement.setString(2, t_member);
				preparedStatement.setString(3, t_content[i]);
				
				int rn =  preparedStatement.executeUpdate();
				
				if(rn < 1 ){
					connection.rollback();
					return;
				}
			}
			
			connection.commit(); 
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			
			try{
				
				if(connection!=null){
					connection.setAutoCommit(true); //트랜잭션 처리를 기본상태로 되돌린다.
					connection.close();
				}
				
				if(preparedStatement!=null)
					preparedStatement.close();
				if(resultSet!=null)
					resultSet.close();
				 
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
			
		}
	}
	public ArrayList<TupyoMultiChecked> getTupyoResult_multi(String t_id, String t_result[]){
		String query = "";
		ArrayList<TupyoMultiChecked> result_list = new ArrayList<TupyoMultiChecked>(); 
		

		try{
			connection = dataSource.getConnection();

			for(int i = 0; i < t_result.length; i++){
				query = "select * from chw_tupyo_recode where t_id = ?";
				int number = 0;
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(t_id));
				ResultSet resultSet = preparedStatement.executeQuery();	
				
				while(resultSet.next()){
					if(resultSet.getString("t_content").equals(t_result[i])){
						number++;
					}
					
					
				}
				String temp_number = ""+number;
				TupyoMultiChecked tmc = new TupyoMultiChecked(temp_number, t_result[i]);
				result_list.add(tmc);
			}
			
			
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
		return result_list;
		
	}
	
	public String[] getTupyoResult(String t_id, String t_result){
		String query = "";
		String result_arr[] = new String[2];
		int number = 0;

		try{
			connection = dataSource.getConnection();

			query = "select * from chw_tupyo_recode where t_id = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(t_id));
			ResultSet resultSet = preparedStatement.executeQuery();	
			
			while(resultSet.next()){
				if(resultSet.getString("t_content").equals(t_result)){
					number++;
				}

				
			}
			result_arr[0] = ""+number;
			result_arr[1] = t_result;
			
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
	public void upHit_multi(String t_id, ArrayList<TupyoMultiChecked> result_list){
		
		String query ="";
		try{
			connection = dataSource.getConnection();
			connection.setAutoCommit(false); // 트랜잭션을 사용하기 위해서 AutoCommit을 정지한다 
			for (int i=0; i<result_list.size(); i++){
				query = "update chw_tupyo_items set t_item_selected = ? where t_id = ? and t_item_content=?";
				 
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(result_list.get(i).getT_item_selected()));
				preparedStatement.setInt(2, Integer.parseInt(t_id));
				preparedStatement.setString(3, result_list.get(i).getT_content());
				
				int rn = preparedStatement.executeUpdate();	
				if(rn < 1){
					connection.rollback();				
				}			
			}
								
			connection.commit();		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			try{
				if(preparedStatement !=null){
					preparedStatement.close();
				}
				if(connection !=null){
					connection.setAutoCommit(true); //트랜잭션 처리를 기본상태로 되돌린다. 
					connection.close();
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void upHit(String result, String t_id, String number, String t_content){
		
		String query ="";
		try{
			connection = dataSource.getConnection();
			connection.setAutoCommit(false); // 트랜잭션을 사용하기 위해서 AutoCommit을 정지한다 
			query = "update chw_tupyo_items set t_item_selected = ? where t_id = ? and t_item_content=?";
					 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(number));
			preparedStatement.setInt(2, Integer.parseInt(t_id));
			preparedStatement.setString(3, t_content);
			
			int rn = preparedStatement.executeUpdate();
			System.out.println(rn);
			
			if(rn < 1){
				connection.rollback();				
			}			
			connection.commit();		
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try{
				if(preparedStatement !=null){
					preparedStatement.close();
				}
				if(connection !=null){
					connection.setAutoCommit(true); //트랜잭션 처리를 기본상태로 되돌린다. 
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
	
	

	public void reg_poll(String title, String writer, String is_duplicated, String item_number, String is_multi_check, String[] t_item_content){
			
			String query ="";
			int t_id = 0;
			try{
				connection = dataSource.getConnection();
				connection.setAutoCommit(false); // 트랜잭션을 사용하기 위해서 AutoCommit을 정지한다 
								
				query = "insert into chw_tupyo (id, title, writer, is_duplicated, reg_date, item_number, is_multi_check) values (chw_tupyo_seq.nextval, ?, ?, ?, sysdate, ?, ?)";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, writer);
				preparedStatement.setString(3, is_duplicated);
				preparedStatement.setInt(4, Integer.parseInt(item_number));
				preparedStatement.setString(5, is_multi_check);
											
				int rn = preparedStatement.executeUpdate();	
				if(rn < 1){
					connection.rollback(); //에러발생시 rollback 처리
				}
				
				query = "select * from chw_tupyo where title=? and writer=?";
				preparedStatement = connection.prepareStatement(query);				
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, writer);
				resultSet = preparedStatement.executeQuery();
				
				
				
				if(resultSet.next()){
					t_id = resultSet.getInt("id");
				}else{
					connection.rollback(); //에러발생시 rollback 처리
				}
				
				for(int i = 0; i < t_item_content.length; i++){
					query = "insert into chw_tupyo_items(pk_id, t_id, t_item_content, t_item_selected, t_title) values (chw_tupyo_items_seq.nextval, ?, ?, ?, ?)";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, t_id);
					preparedStatement.setString(2, t_item_content[i]);
					preparedStatement.setInt(3, 0);
					preparedStatement.setString(4, title);
					
					 rn = preparedStatement.executeUpdate();	
					if(rn < 1){
						connection.rollback(); //에러발생시 rollback 처리
					}
					
				}			
								
				connection.commit(); //데이타 처리시 에러가 없다면 commit 수행 
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(preparedStatement !=null){
						preparedStatement.close();
						
					}
					if(connection !=null){
						connection.setAutoCommit(true); //트랜잭션 처리를 기본상태로 되돌린다. 
						connection.close();
						
					}	
				}catch(SQLException e){
					e.printStackTrace();
				}
			}

		}

	}
