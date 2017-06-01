package poker.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static Connection CONNECTION;

	private DBManager() {
	}

	public static void openConnection(DBConfig config) {

		if (config == null && CONNECTION == null) {
			System.out.println("数据库配置信息错误");
		}

		if (CONNECTION == null) {
			try {
				Class.forName(config.getDriver());
				CONNECTION = DriverManager.getConnection(config.getUrl(),
						config.getUsername(), config.getPassowrd());
			} catch (ClassNotFoundException e) {
				System.out.println("驱动加载失败");
			} catch (SQLException e) {
				System.out.println("建立连接失败");
			}
		}

	}

	public static Connection getConnection() {
		return DBManager.CONNECTION;
	}

	public static void getClose(ResultSet resultSet, Statement statement,
			Connection connection) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
