package poker.util.database.template;

import java.util.Map;

public class MethodUtil {

	public static final String GET= "get";
	
	public static final String SET = "set";
	
	public static String[] getMethodNames(String prefix,Map<String, String> columnPropertys){
		String[] methodNames = new String[columnPropertys.size()];
		int index = 0;
		String property;
		for (String columnName : columnPropertys.keySet()) {
			property = columnPropertys.get(columnName);
			property = Character.toUpperCase(property.charAt(0))
					+ property.substring(1);
			methodNames[index] = prefix + property;
			index++;
		}
		return methodNames;
	}
	
	public static String getPKMethodNames(String prefix,String columnStr){
		columnStr = prefix + Character.toUpperCase(columnStr.charAt(0)) + columnStr.substring(1);
		return columnStr;
	}
}
