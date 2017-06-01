package poker.util.database.template;

import java.util.Map;

/**
 * 抽象实体类，要求所有对应该框架的实体类均要继承该类
 * @author zhouqi
 *
 */
public abstract class Entity {
	
	/**
	 * 表名，由子类的构造函数中赋值
	 */
	public String tableName;
	
	/**
	 * 该实体类对应表的主键，由子类的构造函数中赋值
	 */
	public String pk;
	
	/**
	 * 实体类属性与表中字段的对应关系，由子类进行实现
	 * @return	其对应关系，该Map的key为表字段，value为实体类属性
	 */
	public abstract Map<String, String> getColumnPropertys();
}
