package poker.util.database.template;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 对数据库进行操作
 * @author zhouqi
 *
 */
public class Action {

	private ConnectionManager dbUtil = null;
	private Connection connection = null;
	private int result = 0;
	
	protected void setDbUtil(ConnectionManager dbUtil) {
		this.dbUtil = dbUtil;
		this.connection = this.dbUtil.getConnection();
	}
	
	protected List select(String sql,Class entityClass){
		List list = null;
		try {
			ResultSet rs = this.getStatement().executeQuery(sql);
			list = ORM.readResultSet(rs,entityClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return list;
	}
	
	protected List select(String sql,Object value,Class entityClass){
		List list = null;
		try {
			PreparedStatement ps = this.getPreparedStatement(sql);
			ps.setObject(1, value);
			ResultSet rs = ps.executeQuery();
			list = ORM.readResultSet(rs,entityClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return list;
	}
	
	protected List select(String sql,Object[] values,Class entityClass){
		List list = null;
		try {
			PreparedStatement ps = this.getPreparedStatement(sql);
			for(int i = 0;i < values.length;i++){
				ps.setObject(i+1, values[i]);
			}
			ResultSet rs = ps.executeQuery();
			list = ORM.readResultSet(rs,entityClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return list;
	}

	protected int save(Entity entity){
		String sql = SQL.createInsertSql(entity.tableName , entity.getColumnPropertys());
		try {
			PreparedStatement ps = this.getPreparedStatement(sql);
			String[] methodNames = MethodUtil.getMethodNames(MethodUtil.GET, entity.getColumnPropertys());
			ORM.setPreparedStatement(ps, entity, methodNames,null);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return result;
	}
	
	protected int update(Entity entity){
		int result = 0;
		String sql = SQL.createUpdateSql(entity.tableName , entity.pk , entity.getColumnPropertys());
		try {
			PreparedStatement ps = this.getPreparedStatement(sql);
			String[] methodNames = MethodUtil.getMethodNames(MethodUtil.GET, entity.getColumnPropertys());
			ORM.setPreparedStatement(ps, entity, methodNames,ORM.SET_TYPE_UPDATE);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return result;
	}
	
	protected int executeUpdate(String sql){
		try {
			result = this.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return result;
	}
	
	protected int executeUpdate(String sql , Object value){
		try {
			PreparedStatement ps = this.getPreparedStatement(sql);
			ps.setObject(1, value);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return result;
	}
	
	protected int executeUpdate(String sql , Object[] values){
		try {
			PreparedStatement ps = this.getPreparedStatement(sql);
			for(int i = 0;i < values.length;i++){
				ps.setObject(i+1, values[i]);
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println(sql);
		}
		return result;
	}
	
	protected int getCount(String sql){
		
		try {
			PreparedStatement ps = this.getPreparedStatement(sql);
			ResultSet rs  = ps.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Statement getStatement() throws SQLException{
		Statement statement = this.connection.createStatement();
		dbUtil.addStatement(statement);
		return statement;
	}
	
	private PreparedStatement getPreparedStatement(String sql) throws SQLException{
		PreparedStatement statement = this.connection.prepareStatement(sql);
		dbUtil.addStatement(statement);
		return statement;
	}
	
	private CallableStatement getCallableStatement(String sql) throws SQLException{
		CallableStatement statement = this.connection.prepareCall(sql);
		dbUtil.addStatement(statement);
		return statement;
	}
	
	protected void close(){
		this.dbUtil.closeConnection();
	}
}
