package com.lofter.utils;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class RegistrationUtil {
	public static void save(Properties p) throws BackingStoreException{
		//
		Preferences pre=Preferences.userNodeForPackage(RegistrationUtil.class);//RegistrationUtil.class=>得到com.yc.utils包的路径
		//就可以在注册表生成文件夹，才能区分
		//entry是一个键值对象
		for(Entry<Object,Object>entry:p.entrySet()){
			pre.put(entry.getKey().toString(), entry.getValue().toString());
		}
		pre.flush();
	}
	
	public static String get(String key) throws BackingStoreException{
		//
		if(key!=null &&!"".equals(key)){
			Preferences pre=Preferences.userNodeForPackage(RegistrationUtil.class);
			return pre.get(key, null);//根据key到注册表中查找，找不到，则返回null
		}
		return null;
	}
	
	public static void del(Properties p) throws BackingStoreException{
		//
		Preferences pre=Preferences.userNodeForPackage(RegistrationUtil.class);//RegistrationUtil.class=>得到com.yc.utils包的路径
		//就可以在注册表生成文件夹，才能区分
		//entry是一个键值对象
		for(Entry<Object,Object>entry:p.entrySet()){
			if( entry.getKey().toString().equals("respwd")){
				pre.remove(entry.getKey().toString());
			}
		}
		pre.flush();
	}
	

}
