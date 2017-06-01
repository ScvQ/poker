package poker.util.database.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接管理器
 * @author zhouqi
 *
 */
public class ConnectionManager {
	
	/*private String username = PropertiesUtil.getPropertiesText("jdbc", "username");
	private String password = PropertiesUtil.getPropertiesText("jdbc", "password");
	public static String driver_class_name = PropertiesUtil.getPropertiesText("jdbc", "driver_class_name");
	private String url = PropertiesUtil.getPropertiesText("jdbc", "url");
	
	static{
		try {
			Class.forName(ConnectionManager.driver_class_name);
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败!");
			e.printStackTrace();
		}
	}*/
	
	//定义一个数据库连接
	private Connection connection = null;
	//定义一个Statement的集合,即：一个Connection声明周琦中Statement的回收集合 
	private List<Statement> statements = new ArrayList<Statement>();
	//默认不设置事务
	boolean transactional = false;
	
	/**
	 * 构造函数
	 * @param transactional	是否带有事务
	 * @param dbConfig	数据库连接信息配置
	 */
	public ConnectionManager(boolean transactional,DBConfig dbConfig){
		
		this.transactional = transactional;	//将外层调用传入的事务控制进行注入
		try {
			Class.forName(dbConfig.getDriverClassName());	//加载jdbc驱动
			
			//获取一个数据库连接通道
			this.connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
			//设置不自动提交
			this.connection.setAutoCommit(false);
		} catch (Exception e) {
			 System.out.println("获取连接失败!");  
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection(){
		return this.connection;
	}
	
	/**
	 * 回收一个Statement
	 * @param statement	要回收的Statement
	 */
	public void addStatement(Statement statement){
		this.statements.add(statement);
	}
	
	/**
	 * 关闭所有的已回收的Statement
	 */
	private void closeAllStatement(){
		for(Statement statement:this.statements){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 提交事务
	 */
	private void commit(){
		try {
			if(this.transactional)
				this.connection.commit();
			this.closeAllStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭连接
	 */
	public void closeConnection(){
		try {
			this.commit();
			this.connection.close();
		} catch (SQLException e) {
			System.out.println("关闭连接失败!");
			e.printStackTrace();
		}
	}
	
}
