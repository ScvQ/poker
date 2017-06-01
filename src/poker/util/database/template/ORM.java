package poker.util.database.template;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ORM {
	
	public static final String SET_TYPE_UPDATE = "update";

	public static void setPreparedStatement(PreparedStatement ps,Entity entity,String[] methodNames,String setType) throws Exception{
		Method method = null;
		for (int i = 0; i < methodNames.length; i++) {
			method = entity.getClass().getMethod(methodNames[i]);
			ps.setObject(i + 1, method.invoke(entity));
		}
		if(setType != null && ORM.SET_TYPE_UPDATE.equals(setType)){
			method = entity.getClass().getMethod(MethodUtil.getPKMethodNames(MethodUtil.GET, entity.getColumnPropertys().get(entity.pk)));
			ps.setObject(methodNames.length + 1, method.invoke(entity));
		}
	}
	
	public static List readResultSet(ResultSet rs,Class entityClass){
		List<Object> list = new ArrayList<Object>();
		Object bean = null;
		Map<String, String> columnPropertys = null;
		Method method = null;
		try {
			while(rs.next()){
				bean = entityClass.newInstance();
				if(columnPropertys == null){
					method = bean.getClass().getMethod("getColumnPropertys");
					columnPropertys = (Map<String, String>) method.invoke(bean);
				}
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					String columnName = meta.getColumnName(i + 1);
					Object columnValue = rs.getObject(columnName);
					try {
						PropertyDescriptor pd = new PropertyDescriptor(columnPropertys.get(columnName),bean.getClass());
						method = pd.getWriteMethod();
						
						if(columnValue != null)
							method.invoke(bean, columnValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
