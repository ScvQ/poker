package test.dao;

import poker.util.database.template.DBConfig;


public class DataSource implements DBConfig {

	@Override
	public String getUsername() {
		return "root";
	}

	@Override
	public String getPassword() {
		return "root";
	}

	@Override
	public String getUrl() {
		return "jdbc:mysql:///app";
	}

	@Override
	public String getDriverClassName() {
		return "com.mysql.jdbc.Driver";
	}

}
