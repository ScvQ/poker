package poker.util.servlet;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class RespUtil {

	// 向请求方打印结果
	public static void responseOut(HttpServletResponse response,String msg){
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = null;
         try {
        	 out = response.getWriter();
             out.println(msg);                      
         }catch (Exception e) {
        	 e.printStackTrace();
         }finally {   
        	out.flush();
            out.close();
        }
	}
	
	
}
