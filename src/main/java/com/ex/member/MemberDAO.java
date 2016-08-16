package com.ex.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ex.tupyo.BaseDAO;

public class MemberDAO extends BaseDAO{
	
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public String getIpAddress(){
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();
         
        return ip;
	}
	

	public MemberDAO(){
		super();
	}
	public String check_password(String id, String password){
		String result = "";
		String opassword = "";
		
		try{			
			//connection
			connection = super.dataSource.getConnection();
			
			String query = "select * from chw_member where id = ?";
			//preparedStatement
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){

				opassword = resultSet.getString("password");

			}
			
			if(password.equals(opassword)){
				result="success";
			}
			else{
				result="error";
			}
			
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
	
	// register
	public String register(String id, String password, String name){
		String result = check_id(id);
		if(result.equals("fail"))
			return "error";
		try{			
			//connection
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			String query = "insert into chw_member (id, password, name, curr_user, reg_date, drop_date, pk_mid ) values (?, ?, ?, 'y', sysdate, ?, chw_member_seq.nextval)";
			//preparedStatement
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, null);
			int rn = preparedStatement.executeUpdate();
			if(rn <1){
				connection.rollback();
			}
			connection.commit();		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
	
	public String check_id(String logid){
		String result ="";
		try{			
			//connection
			connection = super.dataSource.getConnection();
			String query = "select * from chw_member where id = ?";
			//preparedStatement
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, logid);

			 resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				result = "fail";
			}
			else{
				result = "success";	
			}
			
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
	
	public ArrayList<MemberDTO> login_check(String logid, String logpassword){

		ArrayList<MemberDTO> mdtos = new ArrayList<MemberDTO>();
		try{
			//connection
			connection = super.dataSource.getConnection();

			//preparedStatement
			String query1 = "select * from chw_member where id = ?";
			preparedStatement = connection.prepareStatement(query1);
			preparedStatement.setString(1, logid);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()){
				
				if(logpassword.equals(resultSet.getString("password"))){
					String id = logid;
					String password = resultSet.getString("password");
					String name = resultSet.getString("name");
					String curr_user =  resultSet.getString("curr_user");
					Date reg_date = resultSet.getDate("reg_date");
					Date drop_date = resultSet.getDate("drop_date");
					int pk_mid = resultSet.getInt("pk_mid");
					System.out.println(curr_user+"spacecheck");
					login(id);
					
					MemberDTO mdto = new MemberDTO(id, password, name, curr_user, reg_date, drop_date, pk_mid);

					mdtos.add(mdto);
					
				}else if(resultSet.getString("curr_user").equals("n  ")){
					MemberDTO mdto = new MemberDTO(null, null, null, null, null, null, -1);
					mdtos.add(mdto);
					update_log(logid, "login_fail");
				}else{
					update_log(logid, "login_fail");
				}
			
			}else{
				update_log(logid, "login_fail");
			
			}
			
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
		
		return mdtos;
	}
	public void login(String loglid){
			
		try{
			//connection
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);

			//preparedStatement
			String query2 = "insert into chw_mlog (pk_lid, mlogid, log_date, log_content, ip_address) values (chw_mlog_seq.nextval, ?, sysdate, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query2);
			preparedStatement.setString(1, loglid);
			preparedStatement.setString(2, "Login");
			preparedStatement.setString(3, getIpAddress());

			int rn = preparedStatement.executeUpdate();
			
			if(rn < 1){
				connection.rollback();
			}
			connection.commit();
			System.out.println(getIpAddress());
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				// connection dispose
				if(connection!=null){

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
	public void logout(String loglid){
				
		try{
			//connection
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			//preparedStatement
			String query2 = "insert into chw_mlog (pk_lid, mlogid, log_date, log_content, ip_address) values (chw_mlog_seq.nextval, ?, sysdate, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query2);
			preparedStatement.setString(1, loglid);
			preparedStatement.setString(2, "Logout");
			preparedStatement.setString(3, getIpAddress());
			
			int rn = preparedStatement.executeUpdate();
			
			if(rn < 1){
				connection.rollback();
			}
			connection.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
		
	}
	public void update_log(String loglid, String log_content){
		
		if(loglid.equals(null)){
			return;
		}
		
		try{
			//connection
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			//preparedStatement
			String query2 = "insert into chw_mlog (pk_lid, mlogid, log_date, log_content, ip_address) values (chw_mlog_seq.nextval, ?, sysdate, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query2);
			preparedStatement.setString(1, loglid);
			preparedStatement.setString(2, log_content);
			preparedStatement.setString(3, getIpAddress());
			
			int rn = preparedStatement.executeUpdate();
			if(rn < 1){
				connection.rollback();
			}
			connection.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				// connection dispose
				if(connection!=null){
					
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
	public void update(String pk_lid, String n_id,String n_password,  String n_name){
		try{
			//connection
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			//preparedStatement
			String query2 = "update chw_member set id=?, password=?, name=? where pk_mid=?";
			
			preparedStatement = connection.prepareStatement(query2);
			preparedStatement.setString(1, n_id);
			preparedStatement.setString(2, n_password);
			preparedStatement.setString(3, n_name);
			preparedStatement.setInt(4, Integer.parseInt(pk_lid));
			
			int rn = preparedStatement.executeUpdate();
			if(rn< 1){
				connection.rollback();
			}
			connection.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
	}
	public void update_info(String pk_lid, String n_id, String n_name){
		try{
			//connection
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			//preparedStatement
			String query2 = "update chw_member set id=?, name=? where pk_mid=?";
			
			preparedStatement = connection.prepareStatement(query2);
			preparedStatement.setString(1, n_id);
			preparedStatement.setString(2, n_name);
			preparedStatement.setInt(3, Integer.parseInt(pk_lid));
			
			int rn = preparedStatement.executeUpdate();
			if(rn< 1){
				connection.rollback();
			}
			connection.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
	}
	public void destroy(String pk_lid){
		try{
			//connection
			connection = super.dataSource.getConnection();
			connection.setAutoCommit(false);
			//preparedStatement
			String query2 = "update chw_member set password=?, name=?, curr_user=?, drop_date=sysdate where pk_mid=?";
			
			preparedStatement = connection.prepareStatement(query2);
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, null);
			preparedStatement.setString(3, "n");
			preparedStatement.setInt(4, Integer.parseInt(pk_lid));
			
			int rn = preparedStatement.executeUpdate();
			if(rn < 1){
				connection.rollback();
			}
			connection.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
		
	}
	
	public ArrayList<MemberLogDTO> member_log(String _mlogid){
		ArrayList<MemberLogDTO> mldtos = new ArrayList<MemberLogDTO>();
		
		try{
			connection = super.dataSource.getConnection();
			String query = "select pk_lid, mlogid, to_char(log_date, 'yyyy-mm-dd hh24:mm:ss') as log_date, log_content, ip_address from chw_mlog where mlogid = ? order by log_date DESC";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, _mlogid);
			
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
				}else {
					log_content = "탈퇴 계정";
				}
				
				String ip_address = resultSet.getString("ip_address");
				
				MemberLogDTO mldto = new MemberLogDTO(pk_lid, mlogid, log_date, log_content, ip_address);
			
				mldtos.add(mldto);
			}
			
		}catch(Exception e){
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
		
		return mldtos;
	}
	
}
