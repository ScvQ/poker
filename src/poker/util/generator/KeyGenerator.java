package poker.util.generator;


import java.util.UUID;

/**
 * 主键生成策略
 * @author zhouqi
 * @date 2016年3月8日 下午1:50:49
 */
public class KeyGenerator {
	
	private static KeyGenerator keyGenerator = null;
	
	/**
	 * key
	 */
	private String key;

	/**
	 * 该类无参构造函数
	 */
	private KeyGenerator() {
		//默认生成key值
		this.key = this.getUUID();
	}
	
	/**
	 * 得到一个UUID，该UUID中的所有-被替换为空字符串
	 * @return	长度为32，由数字和小子字母组成的字符串
	 */
	private String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 得到缺省的Key	缺省生成策略
	 * @return	长度为32，由数字和小子字母组成的字符串
	 */
	public synchronized static String getKey(){
		keyGenerator = new KeyGenerator();
		String k = keyGenerator.key;
		keyGenerator = null;
		return k;
	}
	
}
