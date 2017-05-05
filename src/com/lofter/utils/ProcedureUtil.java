package com.lofter.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import com.lofter.bean.FocusProcedureBean;
import com.lofter.bean.ProcedureBean;




public class ProcedureUtil {

	//查询点赞记录
	public ResultSet doProByTypeOfTable( List<ProcedureBean> listbean){
		ResultSet rs=null;
		Connection con =null;
		try {  
            con=getCon(); 
            CallableStatement cs = null;
            //创建一个ORACLE的OBJECT描述符
    		StructDescriptor recDesc = StructDescriptor.createDescriptor(
    				"TP_ARR5", con);

    		//ORACLE的结构体（模拟多行数据）
			ArrayList<STRUCT> pstruct = new ArrayList<STRUCT>();
			//生成多行数据
			for (ProcedureBean pb : listbean) {
			//	System.out.println(pb);
				Object[] objs = new Object[3];
				objs[0] = pb.getUserid();
				objs[1] = pb.getTopicid();
				objs[2] = pb.getRecord();
				STRUCT item = new STRUCT(recDesc, con, objs);
				pstruct.add(item);
			}
			//转换对象为oracle的数组对象
			oracle.sql.ArrayDescriptor desc = oracle.sql.ArrayDescriptor.createDescriptor("TP_ARR_TBL5", con);  
			//转换为Oracle的数组
			oracle.sql.ARRAY array = new oracle.sql.ARRAY(desc, con, pstruct.toArray()); 
			cs =  con.prepareCall("{call findRecord(?,?)}");
			//设置参数
			cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setArray(2, array);
            
  //          System.out.println(cs.getArray(1).getBaseTypeName());
  //          System.out.println(cs.getObject(1));
            cs.execute();
            con.commit();
            rs=(ResultSet) cs.getObject(1);	
//            while( rs.next() ){
//            	System.out.println("result : \t" + rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
//            }
        } catch (Exception e) {   
  
            e.printStackTrace();  
  
        }
		return rs;  
  
    }    
	
	//查询收藏记录
	public ResultSet doProFindCollect( List<ProcedureBean> listbean){
		ResultSet rs=null;
		Connection con=null;
		try {  
			con=getCon(); 
			CallableStatement cs = null;
            
    		StructDescriptor recDesc = StructDescriptor.createDescriptor(
    				"TP_ARR_COLLECT", con);

			ArrayList<STRUCT> pstruct = new ArrayList<STRUCT>();
			for (ProcedureBean pb : listbean) {
			//	System.out.println(pb);
				Object[] objs = new Object[3];
				objs[0] = pb.getUserid();
				objs[1] = pb.getTopicid();
				objs[2] = pb.getRecord();
				STRUCT item = new STRUCT(recDesc, con, objs);
				pstruct.add(item);
			}
			oracle.sql.ArrayDescriptor desc = oracle.sql.ArrayDescriptor.createDescriptor("TP_ARR_COLLECT_TBL", con);  
            oracle.sql.ARRAY array = new oracle.sql.ARRAY(desc, con, pstruct.toArray()); 
            cs =  con.prepareCall("{call findCollectRecord(?,?)}");
            
            cs.setArray(1, array);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            con.commit();
            rs=(ResultSet) cs.getObject(2);	
//            while( rs.next() ){
//            	System.out.println("result : \t" + rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
//            }
        } catch (Exception e) {  
  
            e.printStackTrace();  
  
        }
		return rs;  
  
    }
	
	//查询关注记录
		public ResultSet doProFindFocusRecord( List<FocusProcedureBean> listbean){
			ResultSet rs=null;
			Connection con=null;
			try {  
				con=getCon(); 
				CallableStatement cs = null;
	            
	    		StructDescriptor recDesc = StructDescriptor.createDescriptor(
	    				"TP_ARR_FOCUS", con);

				ArrayList<STRUCT> pstruct = new ArrayList<STRUCT>();
				for (FocusProcedureBean pb : listbean) {
				//	System.out.println(pb);
					Object[] objs = new Object[3];
					objs[0] = pb.getFocususerid();
					objs[1] = pb.getFocuseduserid();
					objs[2] = pb.getFocusrecord();
					STRUCT item = new STRUCT(recDesc, con, objs);
					pstruct.add(item);
				}
				oracle.sql.ArrayDescriptor desc = oracle.sql.ArrayDescriptor.createDescriptor("TP_ARR_FOCUS_TBL", con);  
	            oracle.sql.ARRAY array = new oracle.sql.ARRAY(desc, con, pstruct.toArray()); 
	            cs =  con.prepareCall("{call findFocusRecord(?,?)}");
	            
	            cs.setArray(1, array);
	            cs.registerOutParameter(2, OracleTypes.CURSOR);
	            cs.execute();
	            con.commit();
	            rs=(ResultSet) cs.getObject(2);	
//	            while( rs.next() ){
//	            	System.out.println("result : \t" + rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
//	            }
	        } catch (Exception e) {  
	  
	            e.printStackTrace();  
	  
	        }
			return rs;  
	  
	    }

	private Connection getCon() throws ClassNotFoundException, SQLException {
		Connection con=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");  
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";  
        con = DriverManager.getConnection(url, "system", "a"); 
        return con;
	}
	
	
}
