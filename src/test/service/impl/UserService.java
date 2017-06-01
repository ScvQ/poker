package test.service.impl;

import poker.util.database.template.Template;
import test.dao.DataSource;
import test.dao.UserDao;
import test.entity.User;
import test.service.IUserService;


public class UserService implements IUserService {

	
	private UserDao userDao = new UserDao();
	
	
	@Override
	public User login(String username, String password) {

		User user = null;
		
		Template template = new Template(true,new DataSource());
		
		boolean bool = this.userDao.checkUsernameAndPassword(username, password, template);
		
		if(bool){
			user = this.userDao.findByUsernameAndPassword(username, password, template);
		}
		
		template.close();
		
		return user;
	}

	
	
}
