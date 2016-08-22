package com.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ex.tupyo.MemberDTO;
import com.ex.tupyo.MemberLogDTO;

public class AdminDAO extends BaseDAO {

	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	public AdminDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public MemberDTO admin_login(String logid, String logpw){
		String result = "";
		String query = "select * from chw_member where not grade = ? and id = ?";
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = null;
		try{
			connection = super.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 9);
			preparedStatement.setString(2, logid);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				if(logpw.equals(resultSet.getString("password"))){
					String id = resultSet.getString("id");
					String password = resultSet.getString("password");
					String name = resultSet.getString("name");
					String curr_user =  resultSet.getString("curr_user");
					Date reg_date = resultSet.getDate("reg_date");
					Date drop_date = resultSet.getDate("drop_date");
					int pk_mid = resultSet.getInt("pk_mid");
					int grade = resultSet.getInt("grade");
					String reg_person = resultSet.getString("reg_person");
					
					mdto = new MemberDTO(id, password, name, curr_user, reg_date, drop_date, pk_mid, grade, reg_person);
					mdao.update_log(logid, "admin_login_success");
					
				}else if(resultSet.getString("curr_user").equals("n  ")){
					mdto = new MemberDTO(null, null, null, null, null, null, -1, -1, null);

					mdao.update_log(logid, "admin_login_fail");
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
		return mdto;
	}
	public ArrayList<MemberDTO> member_list_view(){
		ArrayList<MemberDTO> mdtos = new ArrayList<MemberDTO>();
		String query = "select * from chw_member where not grade = ? and curr_user = ? order by reg_date desc";
		try{
			connection = super.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "y  ");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()){

					String id = resultSet.getString("id");
					String password = resultSet.getString("password");
					String name = resultSet.getString("name");
					String curr_user =  resultSet.getString("curr_user");
					Date reg_date = resultSet.getDate("reg_date");
					Date drop_date = resultSet.getDate("drop_date");
					int pk_mid = resultSet.getInt("pk_mid");
					int grade = resultSet.getInt("grade");
					String reg_person = resultSet.getString("reg_person");
					
					MemberDTO mdto = new MemberDTO(id, password, name, curr_user, reg_date, drop_date, pk_mid, grade, reg_person);
					mdtos.add(mdto);
					
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
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
		return mdtos;
	}
	public MemberDTO user_detail_view(String pk_id){
		MemberDTO mdto = null;
		String query = "select * from chw_member where pk_mid = ?";
		try{
			connection = super.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(pk_id));

			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()){

					String id = resultSet.getString("id");
					String password = resultSet.getString("password");
					String name = resultSet.getString("name");
					String curr_user =  resultSet.getString("curr_user");
					Date reg_date = resultSet.getDate("reg_date");
					Date drop_date = resultSet.getDate("drop_date");
					int pk_mid = resultSet.getInt("pk_mid");
					int grade = resultSet.getInt("grade");
					String reg_person = resultSet.getString("reg_person");
					
					mdto = new MemberDTO(id, password, name, curr_user, reg_date, drop_date, pk_mid, grade, reg_person);

					
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
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
		return mdto;
	}
	public String user_drop(String pk_id){
		String result="";
		String query = "update chw_member set password=?, name=?, curr_user=?, drop_date=sysdate where pk_mid=?";
		try{
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, null);
			preparedStatement.setString(3, "n");
			preparedStatement.setInt(4, Integer.parseInt(pk_id));

			int rn = preparedStatement.executeUpdate();
			
			if (rn > 1){
				connection.rollback();
				result = "fail";
			}
			else{
				result="success";
				connection.commit();

			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				// connection dispose
				if(connection!=null){
					connection.setAutoCommit(true);
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
		
		return result;
	}
	public String user_info_update(String pk_mid, String logid, String name, String grade){
		String result="";
		String query = "update chw_member set name=?, grade = ? where pk_mid=?";
		MemberDAO mdao = new MemberDAO();
		try{
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, Integer.parseInt(grade));
			preparedStatement.setInt(3, Integer.parseInt(pk_mid));

			int rn = preparedStatement.executeUpdate();
			
			if (rn > 1){
				connection.rollback();
				result = "fail";
			}
			else{
				result="success";
				connection.commit();
				mdao.update_log(logid, "admin_info_update");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				// connection dispose
				if(connection!=null){
					connection.setAutoCommit(true);
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
		
		return result;
	}	
	public String user_password_update(String pk_mid, String password){
		String result="";
		String query = "update chw_member set password = ? where pk_mid=?";
		MemberDAO mdao = new MemberDAO();
		try{
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, password);
			preparedStatement.setInt(3, Integer.parseInt(pk_mid));

			int rn = preparedStatement.executeUpdate();
			
			if (rn > 1){
				connection.rollback();
				result = "fail";
			}
			else{
				result="success";
				connection.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				// connection dispose
				if(connection!=null){
					connection.setAutoCommit(true);
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
		
		return result;
	}	
	public ArrayList<MemberLogDTO> user_log_view(String mid){
		ArrayList<MemberLogDTO> mldtos = new ArrayList<MemberLogDTO>();
		String query = "select pk_lid, mlogid, to_char(log_date, 'yyyy-mm-dd hh24:mm:ss') as log_date, log_content, ip_address from chw_mlog where mlogid = ? order by log_date DESC";
		MemberDAO mdao = new MemberDAO();
		try{
			connection = super.dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, mid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int pk_lid = resultSet.getInt("pk_lid");
				String mlogid = resultSet.getString("mlogid");	
				String log_date = resultSet.getString("log_date");
				String log_content = "";
				if((resultSet.getString("log_content")).equals("Login")){
					log_content = "로그인";
				}else if((resultSet.getString("log_content")).equals("Logout")){
					log_content = "로그아웃";
				}else if((resultSet.getString("log_content")).equals("info_update")){
					log_content = "정보수정";
				}else if((resultSet.getString("log_content")).equals("password_update")){
					log_content = "비밀번호 수정";
				}else if((resultSet.getString("log_content")).equals("login_fail")){
					log_content = "로그인 실패";
				}else if((resultSet.getString("log_content")).equals("password_confirm_success")){
					log_content = "비밀번호 확인 성공";
				}else if((resultSet.getString("log_content")).equals("password_confirm_error")){
					log_content = "비밀번호 확인 성공";
				}else if((resultSet.getString("log_content")).equals("password_update_fail")){
					log_content = "비밀번호 변경 실패";
				}else if((resultSet.getString("log_content")).equals("session expire")){
					log_content = "세션 만료";
				}else if((resultSet.getString("log_content")).equals("admin_info_update")){
					log_content = "관리자 정보 수정";
				}else if((resultSet.getString("log_content")).equals("admin_login_fail")){
					log_content = "관리자 로그인 실패";
				}else if((resultSet.getString("log_content")).equals("admin_login_success")){
					log_content = "관리자 로그인 성공";
				}
				else {
					log_content = "탈퇴 계정";
				}
				
				String ip_address = resultSet.getString("ip_address");
				
				MemberLogDTO mldto = new MemberLogDTO(pk_lid, mlogid, log_date, log_content, ip_address);
			
				mldtos.add(mldto);
			}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				// connection dispose
				if(connection!=null){
					connection.setAutoCommit(true);
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
		
		return mldtos;
	}
	
}
