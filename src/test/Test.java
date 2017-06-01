package test;

import java.util.List;

import poker.util.database.template.Template;
import test.dao.DataSource;
import test.entity.User;


public class Test {

	public static void main(String[] args) {
		
		Template template = new Template(true,new DataSource());
		
		
		List<User> list1 = template.select(" SELECT * FROM t_user ", User.class);
		
		for(User u:list1){
			System.out.println(u.getUsername());
		}
		
	  	/*List<User> list2 = template.select(" SELECT * FROM t_user u WHERE u.c_id = ? ", "1001", User.class);
	  	List<User> list3 = template.select(" SELECT * FROM t_user u WHERE u.c_sex = ? AND u.c_age = ? ", new Object[]{2,24}, User.class); 
		
		User user = new User();
		
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setAge(14);
		user.setPassword("123");
		user.setSex(1);
		user.setUsername("小明");
		
		int i = template.save(user);
		
		System.out.println(i);
		
		template.close();
		
		
		
		
		
		
		User user1 = new User();
		user1.setId("a93f695d4cf444b7a0ad56beaf72e041");
		user1.setAge(24);
		user1.setPassword("1234");
		user1.setSex(2);
		user1.setUsername("卧槽");
		
		
		int i1 = template.update(user1);
		
		System.out.println(i1);*/
		
		template.close();
		
		
		
	}

}
