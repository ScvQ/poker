package test.dao;

import java.util.List;

import poker.util.database.template.Template;
import test.entity.User;


public class UserDao {

	
	public boolean checkUsernameAndPassword(String username,String password,Template template){
		
		String sql = " SELECT COUNT(1) FROM t_user WHERE c_username = '" + username + "' AND c_password = '" + password + "' ";
		
		int num = template.getCount(sql);
		
		if(num > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public User findByUsernameAndPassword(String username,String password,Template template){
		
		String sql = " SELECT * FROM t_user WHERE c_username = ? AND c_password = ? ";
		
		List<User> list = template.select(sql, new Object[]{username,password}, User.class);
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
		
	}
	
	
}
