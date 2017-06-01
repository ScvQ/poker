package poker.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import poker.util.string.StringUtil;

public class DateUtil {

	// 获得当前系统时间
	public static Date getCurrentTime() {
		return new Date();
	}

	// 通过字符串获得日期
	public static Date getDateFromStr(String str, String format) {
		if (StringUtil.isNullOrEmpty(str))
			return null;
		try {
			Date date = new SimpleDateFormat(format).parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 通过时间戳获取日期
	public static Date getDateFromTimestamp(long time) {
		Date day = new Date(time);
		return day;
	}

	// 通过日期获得字符串
	public static String getStrFromDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	// 通过今天获取过去或将来的第几天的日期
	public static Date getOnedayByToday(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		return calendar.getTime();
	}

}
