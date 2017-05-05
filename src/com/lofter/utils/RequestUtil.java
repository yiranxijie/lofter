package com.lofter.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public  <T> T parseRequest(HttpServletRequest request,Class<T> c) throws UnsupportedEncodingException, NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		request.setCharacterEncoding("utf-8");
		T t=c.newInstance();
		Map<String,RequestBean> parametermap=parseRequest(request);
		List<Method> setMethods=getAllSetMethod(c);
		for(Method m:setMethods){
			String setmethodname=m.getName();
			RequestBean rb=parametermap.get(setmethodname);
			String methodparametertype=m.getParameterTypes()[0].getName();
			if( rb==null ){
				continue;
			}
			String ss=(((String[])rb.getObj())[0]);
			if("int".equals(methodparametertype)||"java.lang.Interger".equals(methodparametertype)){
				m.invoke(t, Integer.parseInt(ss));
			}else if("float".equals(methodparametertype)||"java.lang.Float".equals(methodparametertype)){
				m.invoke(t, Float.parseFloat(ss));
			}
			else if("double".equals(methodparametertype)||"java.lang.Double".equals(methodparametertype)){
				m.invoke(t, Double.parseDouble(ss));
			}
			else if("long".equals(methodparametertype)||"java.lang.Long".equals(methodparametertype)){
				m.invoke(t, Long.parseLong(ss));
			}else if("java.lang.String".equals(methodparametertype)){
				m.invoke(t, ss);
			}else{
				m.invoke(t, rb.getObj());
			}
			
			
		}
		
		return t;
		
	}
	
	private <T> List<Method> getAllSetMethod( Class<T> c ){
		List<Method> list=new ArrayList<Method>();
		Method[] ms=c.getMethods();
		for(Method m:ms){
			if(m.getName().startsWith("set")){
				list.add(m);
			}
		}
		return list;
	
	}
	
	private Map<String,RequestBean> parseRequest( HttpServletRequest request ){
		Enumeration<String> enu=request.getParameterNames();
		Map<String,RequestBean> map=new HashMap<String,RequestBean>();
		while( enu.hasMoreElements() ){
				String pname=enu.nextElement();
				RequestBean rb=new RequestBean();
				rb.setParematerName( pname );
				rb.setMethodName( getSetMethodName( pname ) );
				rb.setObj(  request.getParameterValues( pname ) );
				map.put( getSetMethodName( pname ), rb );
		
		}
		return map;
		
	
	}
	
	private String getSetMethodName( String parameterName){
		return "set"+parameterName.substring(0,1).toUpperCase()+parameterName.substring(1);
	
	}
	
	class RequestBean{
		private String parematerName;
		private String methodName;
		private Object obj;
		
		public String getParematerName() {
			return parematerName;
		}
		public void setParematerName(String parematerName) {
			this.parematerName = parematerName;
		}
		public String getMethodName() {
			return methodName;
		}
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}
		public Object getObj() {
			return obj;
		}
		public void setObj(Object obj) {
			this.obj = obj;
		}
		
	}
	
	
	
	

}
