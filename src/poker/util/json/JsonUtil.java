package poker.util.json;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {
	/**
	 * 解读json串
	 * @param jsonStr
	 * @param ifCharset 是否设置字符集  0为不设置 大于0为设置   全英文不需要设置
	 * @return	返回一个Map<String,Object>
	 * 读取Map中值  例：String accessToken = map.get("access_token").toString();
	 */
	public static Map readMapJson(String jsonStr,int ifCharset){
		ObjectMapper mapper = new ObjectMapper();
		Map map = null;
		try {
			if(ifCharset>0){
				jsonStr = new String(jsonStr.getBytes("ISO-8859-1"),"utf-8");
			}
			map = mapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			map = null;
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 简单将Json串转换为一个对象
	 * @param json
	 * @param entityClass
	 * @return
	 */
	public static Object readJsonToObject(String json,Class entityClass){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return JSONObject.toBean(jsonObject,entityClass);
	}
	
	
	
	/**
	 * 将Json串转换为一个List  且通过时间格式化类型转换Date
	 * @param json
	 * @param entityClass
	 * @return
	 */
	public static List readJsonToListFormatDate(String json,Class entityClass){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
		
		JSONArray jsonArray= JSONArray.fromObject(json,jsonConfig);
		return JSONArray.toList(jsonArray, entityClass);
	}
	
	/**
	 * 简单将一个对象转换为Json
	 * @param object	Map   Entity
	 * @return
	 */
	public static String getJsonFromObject(Object object){
		if(object == null)
			return null;
		JSONObject json = JSONObject.fromObject(object);
		return json.toString();
	}
	
	/**
	 * 简单方式将一个集合类型转换为Json
	 * @param object	List
	 * @return
	 */
	public static String getJsonFromArray(Object object){
		if(object == null)
			return null;
		JSONArray json = JSONArray.fromObject(object);
		return json.toString();
	}
	
	
	/**
	 * 将一个对象类型转换为Json    其中所包含的Date类型被格式化
	 * @param object	单个实体类	Map Entity
	 * @return
	 */
	public static String getJsonFromObjectFormatDate(Object object){
		if(object == null)
			return "";
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd'T'HH:mm:ss"));
		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		
		return jsonObject.toString();
	}
	
	/**
	 * 将一个集合类型转换为Json    其中所包含的Date类型被格式化
	 * @param object	Array数组  List
	 * @return
	 */
	public static String getJsonFromArrayFormatDate(Object object){
		if(object == null)
			return "";
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd'T'HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
		
		return jsonArray.toString();
	}
	
}
