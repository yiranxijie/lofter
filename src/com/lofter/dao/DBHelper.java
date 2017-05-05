package com.lofter.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.lofter.utils.YcUtils;
import com.sina.sae.util.SaeUserInfo;

public class DBHelper {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
//	private Object obj;
	
	//静态块：生命周期长，程序一加载就会执行静态块内容
	//提前注册驱动， 只执行一次
	static{
		try {
			Class.forName( MyProperties.getInstance().getProperty("driverClassName"));
		} catch (Exception e) {
			e.printStackTrace();
			YcUtils.error(e);
			System.out.println(  e.getStackTrace());
		}
	}
	
	//获取连接的方法    不能放到静态块，因为它有 return con, 返回的Connection对象要给后面的类用
	public Connection getCon() throws SQLException, IOException, NamingException{
		//1.jdbc方案    效率低
	//	con=DriverManager.getConnection( MyProperties.getInstance().getProperty("url"), MyProperties.getInstance() );
		
		//2.tomcat 的jndi提供的连接池方案，产品用
	//	Context initContext = new InitialContext();
			//java:comp/env   默认的context名字
	//	Context envContext  = (Context)initContext.lookup("java:/comp/env");
			//jdbc/bbs_oracle  子context名字
	//	DataSource ds = (DataSource)envContext.lookup("jdbc/bbs_oracle");
	//	con = ds.getConnection();	
		
		//3.测试方案：自带dbcp连接池
		con=null;
		try{
			DataSource ds=BasicDataSourceFactory.createDataSource(  MyProperties.getInstance());
			con=ds.getConnection();
		}catch( Exception e){
			throw new RuntimeException(e);
		}
		
//		// 使用从库读数据
//		String URL="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_bbs";
//		// 通过SaeUserInfo提供的静态方法获取应用的access_key和secret_key
//		String Username=SaeUserInfo.getAccessKey();
//		String Password=SaeUserInfo.getSecretKey();
//		String Driver="com.mysql.jdbc.Driver";
//		try {
//			Class.forName(Driver).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		Connection con=DriverManager.getConnection(URL,Username,Password);
		
		return con;
	}
	
	/**
	 * 基本上每一个方法都涉及 建立连接con、创建语句对象pstmt、设置sql参数几个步骤
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NamingException 
	 */
	public void prepareWork( String sql, List<Object> params ) throws SQLException, IOException, NamingException{
		con=this.getCon();
		pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);	//有些结果要求光标滚动
		setParams(pstmt,params);
	}
	
	/**
	 * 给pstmt对象设置参数的方法
	 * @throws SQLException
	 */
	private void setParams( PreparedStatement pstmt, List<Object> params) throws SQLException{
		if( params!=null && params.size()>0 ){
			for( int i=0;i<params.size();i++){
				pstmt.setString(i+1, params.get(i).toString() );
			}
		}
	}
	
	/**
	 * 增删改的操作 sql：语句，有可能有？，也有可能没有， params：参数值的集合
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NamingException 
	 * 
	 * */
	public int doUpdate( String sql, List<Object> params) throws SQLException, IOException, NamingException{
		int result=-1;
		try{
			prepareWork(sql,params);
			result=pstmt.executeUpdate();
		}finally{
			closeAll( pstmt,con,null);
		}
		return result;
	}

	/**
	 * 增删改的操作 sql：语句，有可能有？，也有可能没有， params：参数值的集合
	 * @throws Exception 
	 * 
	 * */
	public int doUpdate( List<String> sqls,List<List<Object>>  params) throws Exception {
		int result=-1;
		try{
			con=this.getCon();
			con.setAutoCommit(false);	//关闭隐式事务，java默认是隐式事务提交，而数据库是显式事务提交，即自己手动提交
			if( sqls!=null && sqls.size()>0 ){
				for( int i=0;i<sqls.size();i++){
					String sql=sqls.get(i);
					pstmt=con.prepareStatement(sql);
					setParams(pstmt,params.get(i));
					result=pstmt.executeUpdate();
				}
			}
			con.commit();	//手动提交
		}catch (Exception ex){	
			con.rollback();		// 如果发生异常， 则回滚
			ex.printStackTrace();		//这种方式只能将异常的堆栈信息输出到控制台，不能永久保存
			YcUtils.error(ex);			//使用日志则可将信息永久保存
			throw ex;		// 这里必须要用try、catch，但又不做处理，所以在这里把异常抛出
		}finally{
			con.setAutoCommit(true);	//恢复现场
			closeAll( pstmt,con,null);
		}
		return result;
	}
	
	/**
	 * 聚合函数查询	多个结果只取一个
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NamingException 
	 * */
	public double findDouble( String sql,List<Object> params) throws SQLException, IOException, NamingException{
		double result=0;
		
		try{
			prepareWork(sql,params);
			rs=pstmt.executeQuery();	// select * from bankaccount where id=1     rs
			if( rs.next()){
					result=rs.getDouble(1);
			}
		}finally{
			closeAll(pstmt,con,rs);
		}
		return result;
	}
	
	/**
	 * 查询： 返回一个Map对象，只查一个对象，如果有多个对象，则不能用这个方法， select * from 表名 where id=1;
	 * @throws NamingException 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws Exception 
	 * */
	public Map<String,String> findSingleObject (String sql,List<Object> params) throws SQLException, IOException, NamingException{
		Map<String,String> map=null;
		
		try{
			prepareWork(sql,params);
			rs=pstmt.executeQuery();	//"select * from bankaccount where id=1"
			List<String> columnNames=getAllColumnNames(rs);		//[ID, BALANCE]
			
			if( rs.next()){
				if( rs.isLast()){
					map=new HashMap<String,String>();
					for(String cn:columnNames){			//第一次  {ID=1}
						map.put(cn, rs.getString(cn));	//第二次  {BALANCE=210, ID=1}
					}
				}else{
					throw new RuntimeException("查询的数据有多条，请使用本类中的  findMutiObject()");
				}
			}
		}finally{
			closeAll(pstmt,con,rs);
		}
		return map;
	}
	
	/**
	 * 查询： 返回多个Map对象 select * from 表名 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NamingException 
	 * @throws Exception 
	 * */
	public List<Map<String,String>> findMutiObject(String sql,List<Object> params ) throws SQLException, IOException, NamingException{
		Map<String,String> map=null;
		List<Map<String,String>> listMap=null;
		List<String> columnNames=null;
		
		try{
			prepareWork(sql,params);
			rs=pstmt.executeQuery();
			columnNames=getAllColumnNames(rs);
				
			listMap=new ArrayList<Map<String,String>>();
				
			while( rs.next()){
				map=new HashMap<String,String>();
				for(String cn:columnNames){
				map.put(cn, rs.getString(cn));
			}
				listMap.add(map);
		}
		}finally{
			closeAll(pstmt,con,rs);
		}
		return listMap;
	}
	
	
	/**
	 * 查询
	 *	 T->BankAccount     c-> BankAccount的实例 
	 * @param <T>
	 * @param c
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception
	 */
	public <T> T findSingleObject( Class<T> c,String sql,List<Object> params) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, Exception {
		T obj=null;
		try{
			prepareWork(sql,params);
			rs=pstmt.executeQuery();
			List<String> columnName=getAllColumnNames(rs);	//取出所有的列名
			List<String> setMethodName=transferMethodName( columnName, "set");	//将所有的列名转为对应的方法名。 setXXX()
			
			//获取BankAccount实例的所有方法
			Method []methods=c.getDeclaredMethods();
			
			if( rs.next()){
				if( rs.isLast()){
					obj=c.newInstance();	//通过c创建一个对象      相当于  BankAccount ba=new BankAccount()
					invokeWork(obj,columnName,setMethodName,methods);
				}else{
					throw new Exception("查询的数据有多条，请使用本类中的  findMutiObject()");
				}
			}
		}finally{
			closeAll(pstmt,con,rs);
		}
		return  obj;
	}
	
	//获取所有列名
	public List<String> getAllColumnNames( ResultSet rs) throws SQLException{
		List<String> columnNames=new ArrayList<String>();
		if(rs!=null){
			for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
				columnNames.add(rs.getMetaData().getColumnName(i));
			}
		}
		return columnNames;
	}
	
	/**
	 * 提取出激活方法
	 * @param columnName
	 * @param setMethodName
	 * @param methods
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void invokeWork(Object obj,List<String> columnName,List<String> setMethodName,Method []methods) throws InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException {
	//	obj=c.newInstance();	//通过c创建一个对象      相当于  BankAccount ba=new BankAccount()
		for( int i=0;i<columnName.size();i++){	//遍历列名集合，取出列名拼接方法名
			String cn=columnName.get(i);
			String smn=setMethodName.get(i);	//拼接好后的方法名
			
			//遍历methods[]，与拼接好的方法名进行比较，如果存在，则激活、传值
			for( int j=0;j<methods.length;j++){
				Method method=methods[j];
				if( method.getName().equals(smn) ){
					if( method.getParameterTypes()[0].getName().equals("java.lang.Integer") || method.getParameterTypes()[0].getName().equals("int")){
						int r=rs.getInt(cn);	//获取列名为cn的值
						method.invoke(obj, r);
						break;
					}else if(method.getParameterTypes()[0].getName().equals("java.lang.Double") || method.getParameterTypes()[0].getName().equals("double")){
						double r=rs.getDouble(cn);
						method.invoke(obj, r);
						break;
					}else if(method.getParameterTypes()[0].getName().equals("java.lang.Float") || method.getParameterTypes()[0].getName().equals("float")){
						float r=rs.getFloat(cn);
						method.invoke(obj, r);
						break;
					}else if(method.getParameterTypes()[0].getName().equals("java.sql.Blob") || method.getParameterTypes()[0].getName().equals("Blob")){
						Blob r=rs.getBlob(cn);
						method.invoke(obj, r);
						break;
					}else if(method.getParameterTypes()[0].getName().equals("java.lang.Boolean") || method.getParameterTypes()[0].getName().equals("boolean")){
						boolean r=rs.getBoolean(cn);
						method.invoke(obj, r);
						break;
					}else if(method.getParameterTypes()[0].getName().equals("java.util.Date") || method.getParameterTypes()[0].getName().equals("Date")){
						Date r=rs.getDate(cn);
						method.invoke(obj, r);
						break;
					}else{
						String r=rs.getString(cn);
						method.invoke(obj, r);
						break;
					}
				}
			}
		}
	}

	/**
	 * 查询
	 * @param <T>
	 * @param cs
	 * @param sql
	 * @param params
	 * @return
	 * @throws NamingException 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	public <T> List<T> findMutiObject( Class<T> c,String sql,List<Object> params) throws SQLException, IOException, NamingException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<T> objs=new ArrayList<T>();
		T obj=null;
		
		try{
			prepareWork(sql,params);
			
				rs=pstmt.executeQuery();
				List<String> columnName=getAllColumnNames(rs);	//取出所有的列名
				List<String> setMethodName=transferMethodName( columnName, "set");	//将所有的列名转为对应的方法名。 setXXX()
				Method []methods=c.getDeclaredMethods();
				while( rs.next()){
						obj=(T) c.newInstance();
						invokeWork(obj,columnName,setMethodName,methods);
						objs.add(obj);
					}
					
		}finally{
			closeAll(pstmt,con,rs);
		}
		return   objs;
	}
	
	
		
	/**
	 * 关闭方法
	 * */
	private void closeAll( PreparedStatement pstmt, Connection con, ResultSet rs){
		if( rs!=null ){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				YcUtils.error(e);
			}
		}
		if( pstmt!=null ){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				YcUtils.error(e);
			}
		}
		if( con!=null ){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				YcUtils.error(e);
			}
		}
	}
	
	/**
	 * 拼接方法名的方法
	 * */
	public List<String> transferMethodName(List<String> columnName, String methodType){
		List<String> setMethodNames=new ArrayList<String>();
		if( columnName!=null && columnName.size()>0){
			for(String cn:columnName){
				StringBuffer sb=new StringBuffer(methodType);
				sb.append( cn.substring(0, 1).toUpperCase() ).append(  cn.substring(1).toLowerCase() );
				setMethodNames.add( sb.toString());
			}
		}
		return setMethodNames;
	}
	
}
