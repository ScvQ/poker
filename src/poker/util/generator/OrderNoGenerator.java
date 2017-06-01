package poker.util.generator;


import java.util.Date;

import poker.util.date.DateUtil;



public class OrderNoGenerator {

private static OrderNoGenerator generator = null;
	
	/**
	 * 邀请码
	 */
	private String code;
	
	/**
	 * 该类无参构造函数
	 */
	private OrderNoGenerator(){
		
		this.code = DateUtil.getStrFromDate(new Date(), "yyyy-MMdd-HHmm-ssSS");
		
	}
	
	/**
	 * 得到缺省的邀请码生成策略
	 * @return	默认长度32位，由数字和小写字母组成的字符串
	 */
	public synchronized static String create(){
		generator = new OrderNoGenerator();
		String key = generator.code;
		generator = null;
		return key;
	}
	
}
