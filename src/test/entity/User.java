package test.entity;

import java.util.HashMap;
import java.util.Map;

import poker.util.database.template.Entity;


public class User extends Entity {
	
	public User(){
		super.pk = "c_id";
		super.tableName = "t_user";
	}

	@Override
	public Map<String, String> getColumnPropertys() {
		Map<String,String> map = new HashMap<String, String>();
		// 表字段 , 实体属性
		map.put("c_id", "id");
		map.put("c_username", "username");
		map.put("c_password", "password");
		map.put("c_sex", "sex");
		map.put("c_age", "age");
		return map;
	}
	
	
	private String id;
	
	private String username;
	
	private String password;
	
	private int sex;
	
	private int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	
	

}
