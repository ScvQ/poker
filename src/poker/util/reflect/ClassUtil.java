package poker.util.reflect;

import java.lang.reflect.Method;

public class ClassUtil {
	
	//从实例对象中获取类信息
	public void getClassMsgFromInstance(Object obj){
		Class c = obj.getClass();
		System.out.println("类的名称是:"+c.getName());
		
		//getMethods()获取的是所有的public函数，包括从父类继承来的
		//getDeclaredMethods()获取所有自己声明的方法，不问访问权限
		Method[] m = c.getMethods();
//		Method[] m = c.getDeclaredMethods();
		
		for(int i=0;i<m.length;i++){
			Class returnType = m[i].getReturnType();
			System.out.print(returnType.getName()+" "+m[i].getName()+"(");
			Class[] paramTypes = m[i].getParameterTypes();
			for(Class cc:paramTypes){
				System.out.println(cc.getName()+",");
			}
			System.out.println(")");
		}
	}
	

}
