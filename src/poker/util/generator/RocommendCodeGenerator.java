package poker.util.generator;


import java.util.UUID;

/**
 * 邀请码声称策略
 * @author zhouqi
 * @date 2016年6月9日 下午11:40:39
 */
public class RocommendCodeGenerator {
	
	private static RocommendCodeGenerator generator = null;
	
	/**
	 * 邀请码
	 */
	private String code;
	
	/**
	 * 该类无参构造函数
	 */
	private RocommendCodeGenerator(){
		
		this.code = UUID.randomUUID().toString().replace("-", "");
		
	}
	
	/**
	 * 得到缺省的邀请码生成策略
	 * @return	默认长度32位，由数字和小写字母组成的字符串
	 */
	public synchronized static String create(){
		generator = new RocommendCodeGenerator();
		String key = generator.code;
		generator = null;
		return key;
	}
	
}
