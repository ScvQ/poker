package poker.util.database.template;

import java.util.List;


/**
 * 数据库执行方法模板类
 * @author zhouqi
 *
 */
public class Template extends Action {
	
	/**
	 * 不带事务的模板
	 * @param dbConfig	数据库连接参数配置接口
	 */
	public Template(DBConfig dbConfig){
		super.setDbUtil(new ConnectionManager(false,dbConfig));
	}
	
	/**
	 * 可以带事务的模板
	 * @param transactional	当该参数为true时，该template所执行的操作均带有事务   
	 * @param dbConfig	数据库连接参数配置接口
	 */	
	public Template(boolean transactional,DBConfig dbConfig){
		super.setDbUtil(new ConnectionManager(transactional,dbConfig));
	}

	/**
	 * 执行一条查询sql语句
	 * 
	 * @param	sql				要执行的sql语句
	 * @param	entityClass		返回结果集对应的实体类的Class
	 * @return	要查询的结果集对应的实体类集合
	 */
	public List select(String sql,Class entityClass){
		return super.select(sql,entityClass);
	}
	
	/**
	 * 执行一条可带有参数的sql语句，其中，需要注入的参数以 ? 代替,该方法只能够存在一个?
	 * @param sql		要执行的sql语句
	 * @param value		sql语句中?所对应的值
	 * @param entityClass	返回结果集对应的实体类的Class
	 * @return	要查询的结果集对应的实体类集合
	 * 
	 */
	public List select(String sql,Object value,Class entityClass){
		return super.select(sql,value,entityClass);
	}
	
	/**
	 * 执行一条可带有参数的sql语句，其中，需要注入的参数以 ? 代替,该方法可以存在多个?
	 * @param sql		要执行的sql语句
	 * @param value		sql语句中?所对应的值所组成的数组，注意：?的顺序跟该数组中值的顺序需保持一致
	 * @param entityClass	返回结果集对应的实体类的Class
	 * @return	要查询的结果集对应的实体类集合
	 * 
	 */
	public List select(String sql,Object[] values,Class entityClass){
		return super.select(sql,values,entityClass);
	}
	
	/**
	 * 执行一条查询数量的sql,其本质可执行大部分的数学函数
	 * @param sql		要执行的sql语句
	 * @return	返回计算数值
	 */
	public int getCount(String sql){
		return super.getCount(sql);
	}
	
	/**
	 * 将一个实体类存入数据库中，要求主键不能为空，主键需自行生成
	 * @param	entity	要存入数据库中的实体类
	 * @return	受影响的行数
	 */
	public int save(Entity entity){
		return super.save(entity);
	}
	
	/**
	 * 执行一条插入sql语句
	 * @param sql	要执行的sql语句
	 * @return	受影响的行数
	 */
	public int insert(String sql){
		return super.executeUpdate(sql);
	}
	
	/**
	 * 执行一条可带有一个参数的插入sql语句，该sql语句中的参数以?代替,注意：该sql语句中只能存在一个?
	 * @param sql		要执行的sql语句
	 * @param value		sql语句中?所对应的值
	 * @return	受影响的行数
	 */
	public int insert(String sql,Object value){
		return super.executeUpdate(sql,value);
	}
	
	/**
	 * 执行一条可带有多个参数的插入sql语句，该sql语句中的参数以?代替
	 * @param sql		要执行的sql语句
	 * @param value		sql语句中?所对应的值所组成的数组
	 * @return	受影响的行数
	 */
	public int insert(String sql,Object[] values){
		return super.executeUpdate(sql,values);
	}
	
	/**
	 * 修改一个实体类
	 * @param entity	要被修改的实体类对象，要求该对象主键不能为空
	 * @return	受影响的行数
	 */
	public int update(Entity entity){
		return super.update(entity);
	}
	
	/**
	 * 执行一条修改sql语句
	 * @param sql	要执行的sql语句
	 * @return	受影响的行数
	 */
	public int update(String sql){
		return super.executeUpdate(sql);
	}
	
	/**
	 * 执行一条可带有一个参数的修改sql语句
	 * @param sql		要执行的sql语句，该sql语句中的参数以?代替,注意：该sql语句中只能存在一个?
	 * @param value		该sql语句中的?所对应的值
	 * @return	受影响的行数
	 */
	public int update(String sql,Object value){
		return super.executeUpdate(sql,value);
	}
	
	/**
	 * 执行一条可带有多个参数的修改sql语句
	 * @param sql		要执行的sql语句，该sql语句中的参数以?代替
	 * @param values	该sql语句中的?所对应的值所组成的数组
	 * @return	受影响的行数
	 */
	public int update(String sql,Object[] values){
		return super.executeUpdate(sql,values);
	}
	
	/**
	 * 执行一条删除sql语句
	 * @param sql	要执行的sql语句
	 * @return	受影响的行数
	 */
	public int delete(String sql){
		return super.executeUpdate(sql);
	}
	
	/**
	 * 执行一条可带有一个参数的删除sql语句，该sql语句中的参数以?代替,注意：该sql语句中只能存在一个?
	 * @param sql		要执行的sql语句
	 * @param value		该sql语句中的?所对应的值
	 * @return			受影响的行数
	 */
	public int delete(String sql,Object value){
		return super.executeUpdate(sql,value);
	}
	
	/**
	 * 执行一条可带有一个参数的删除sql语句，该sql语句中的参数以?代替
	 * @param sql		要执行的sql语句
	 * @param value		该sql语句中的?所对应的值所组成的数组
	 * @return			受影响的行数
	 */
	public int delete(String sql,Object[] values){
		return super.executeUpdate(sql,values);
	}
	
	/**
	 * 关闭当前template，执行该方法之后会将事务提交
	 */
	public void close(){
		super.close();
	}
	
}
