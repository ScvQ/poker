package poker.util.string;


import java.util.List;
import org.apache.commons.lang.StringUtils;

public class StringUtil {
	
	//判断字符串是否为空
	public static boolean isNullOrEmpty(String obj) {
		return obj == null || "".equals(obj.toString().trim());
	}
	
	//将emoji表情替换成*
	public static String filterEmoji(String source) {  
        if(StringUtils.isNotBlank(source)){  
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");  
        }else{  
            return source;  
        }  
    }  
	
	public static String SqlIn(String ids){
		if(StringUtil.isNullOrEmpty(ids))
			return null;
		String[] idArr = ids.split(",");
		StringBuffer sb = new StringBuffer();
		for(String str:idArr){
			sb.append("'");
			sb.append(str);
			sb.append("'");
			sb.append(",");
		}
		ids = sb.toString();
		ids = ids.substring(0, ids.length() - 1);
		return ids;
	}
	
	public static String inDouHao(List<String> list){
		StringBuffer sb = new StringBuffer();
		for(String str:list){
			sb.append(str);
			sb.append(",");
		}
		String s = sb.toString();
		s = s.substring(0, s.length() - 1);
		return s;
	}
	
}
