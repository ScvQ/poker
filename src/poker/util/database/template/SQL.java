package poker.util.database.template;

import java.util.Map;

public class SQL {
	
	public static final String PARAM_TABLE = "@TABLE";
	public static final String PARAM_COLUMN = "@COLUMN";
	public static final String PARAM_ID = "@ID";
	public static final String PARAM_SET = "@SET";
	public static final String PARAM_VALUES = "@VALUES";
	
	
	public static final String SQL_SELECT = " SELECT @COLUMN FROM @TABLE WHERE @ID = ? ";
	
	public static final String SQL_INSERT = " INSERT INTO @TABLE(@COLUMN) VALUES (@VALUES) ";
	
	public static final String SQL_UPDATE = " UPDATE @TABLE SET @SET WHERE @ID = ? ";
	
	public static final String SQL_DELETE = " DELETE FROM @TABLE WHERE @ID = ? ";
	
	
	public static String createInsertSql(String tableName,Map<String, String> columnPropertys){
		String columnString = "";
		String valueString = "";
		
		for (String columnName : columnPropertys.keySet()) {
			columnString += columnName + ", ";
			valueString += "?, ";
		}
		columnString = columnString.substring(0, columnString.length() - 2);
		valueString = valueString.substring(0, valueString.length() - 2);
		
		String sql = SQL.SQL_INSERT.replace(SQL.PARAM_TABLE,tableName);
		sql = sql.replace(SQL.PARAM_COLUMN,columnString);
		sql = sql.replace(SQL.PARAM_VALUES,valueString);
		return sql;
	}
	
	public static String createUpdateSql(String tableName,String pk,Map<String,String> columnPropertys){
		String setString = "";
		for(String columnName:columnPropertys.keySet()){
			setString += columnName + " = ? , ";
		}
		setString = setString.substring(0, setString.length() - 2);
		    
		String sql = SQL.SQL_UPDATE.replace(SQL.PARAM_TABLE,tableName);
		sql = sql.replace(SQL.PARAM_SET, setString);
		sql = sql.replace(SQL.PARAM_ID, pk);
		return sql;
	}
}
