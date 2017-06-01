package poker.util.session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	enum SessionType{
		login,admin,channel
	}
	
	public static void setLogin(HttpServletRequest request,Login login){
		
		HttpSession session = request.getSession();
		session.setAttribute(SessionType.login.toString(), login);
		
	}

	public static Login getLogin(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute(SessionType.login.toString());
		Login login = new Login();
		
		if(obj != null)
			login = (Login) obj;
		else
			login.setId("0");
		
		return login;
	}
	
	public static void setAdmin(HttpServletRequest request,Admin admin){
		HttpSession session = request.getSession();
		session.setAttribute(SessionType.admin.toString(), admin);
	}
	
	public static Admin getAdmin(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute(SessionType.admin.toString());
		
		if(obj != null)
			return (Admin) obj;
		else
			return null;
		
	}
	
	
	
	public static void setChannel(HttpServletRequest request,Channel channel){
		HttpSession session = request.getSession();
		session.setAttribute(SessionType.channel.toString(), channel);
	}
	
	
	
	public static Channel getChannel(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute(SessionType.channel.toString());
		
		
		if(obj != null)
			return (Channel)obj;
		else
			return null;
		
	}
	
	
}
