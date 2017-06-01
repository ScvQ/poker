package poker.util.http;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * @author zhouqi
 * @date 2016年6月9日 下午11:43:45
 */
public class HttpUtil {
	
	public static String connectHttpsByPost(String urlStr,File file) throws Exception{
		String result =null;  

		URL url = new URL(urlStr);
		
		URLConnection con = url.openConnection();
		
		con.setDoInput(true);  
		  
		con.setDoOutput(true);  
		  
		con.setUseCaches(false); // post方式不能使用缓存  
		  
		// 设置请求头信息  
		  
		con.setRequestProperty("Connection", "Keep-Alive");  
		  
		con.setRequestProperty("Charset", "UTF-8");  
		// 设置边界  
		  
		String BOUNDARY = "----------" + System.currentTimeMillis();  
		  
		con.setRequestProperty("Content-Type",  
		        "multipart/form-data; boundary="  
		  
		        + BOUNDARY);  
		  
		// 请求正文信息  
		  
		// 第一部分：  
		  
		StringBuilder sb = new StringBuilder();  
		  
		sb.append("--"); // 必须多两道线  
		  
		sb.append(BOUNDARY);  
		  
		sb.append("\r\n");  
		  
		sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""  
		  
		        + file.getName() + "\"\r\n");  
		  
		sb.append("Content-Type:application/octet-stream\r\n\r\n");  
		  
		byte[] head = sb.toString().getBytes("utf-8");  
		  
		// 获得输出流  
		  
		OutputStream out = new DataOutputStream(con.getOutputStream());  
		  
		// 输出表头  
		  
		out.write(head);  
		  
		// 文件正文部分  
		  
		// 把文件已流文件的方式 推入到url中  
		  
		DataInputStream in = new DataInputStream(new FileInputStream(file));  
		  
		int bytes = 0;  
		  
		byte[] bufferOut = new byte[1024];  
		  
		while ((bytes = in.read(bufferOut)) != -1) {  
		  
		    out.write(bufferOut, 0, bytes);  
		  
		}  
		  
		in.close();  
		  
		// 结尾部分  
		  
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
		  
		out.write(foot);  
		  
		out.flush();  
		  
		out.close();  
		  
		StringBuffer buffer = new StringBuffer();  
		  
		BufferedReader reader = null;  
		  
		try {  
		  
		    // 定义BufferedReader输入流来读取URL的响应  
		  
		    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
		  
		    String line = null;  
		  
		    while ((line = reader.readLine()) != null) {  
		  
		        buffer.append(line);  
		  
		    }  
		  
		    if (result == null) {  
		  
		        result = buffer.toString();  
		  
		    }  
		  
		} catch (IOException e) {  
		  
		    System.out.println("发送POST请求出现异常！" + e);  
		  
		    e.printStackTrace();  
		  
		    throw new IOException("数据读取异常");  
		  
		} finally {  
		  
		    if (reader != null) {  
		  
		        reader.close();  
		  
		    }  
		  
		}  
		return result;  
	}

	
	/**
	 * get方式请求一个远程地址
	 * @param url
	 * @return
	 */
	public static String get(String url){
		String result = null;
		
		HttpClient httpclient = new HttpClient();
		HttpMethod method = new GetMethod(url);
		
		method.setRequestHeader("Content-type", "text/xml; charset=utf-8");
		
		try {
			httpclient.executeMethod(method);
			result = method.getResponseBodyAsString();
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		} 
	
		return result;

	}
	
	public static String post(String url,String postStr){
		String resultStr = null;
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-type", "text/xml; charset=utf-8"); //设置头
		
		//method.setRequestHeader("Content-type", "application/json; encoding=utf-8"); //设置头
		method.setRequestBody(postStr);				//设置内容
        HttpClient httpclient = new HttpClient();      		// 创建 HttpClient的实例
        try {
			int result = httpclient.executeMethod(method);//执行请求
			resultStr = method.getResponseBodyAsString();	// 返回内容
		} catch (Exception e) {
			e.printStackTrace();
		}  		
        method.releaseConnection();                                 // 释放连接	
		return resultStr;
	}
	
	
}
