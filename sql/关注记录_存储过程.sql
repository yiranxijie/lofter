 drop procedure findFocusRecord
 drop package focus_pkg
 drop table tb_temp_profocus
 drop type tp_arr_focus_tbl
 drop type tp_arr_focus

------------在数据库建立一个type,对应JAVA端要传入的对象结构 :  
create or replace type tp_arr_focus as Object    
(    
  focususerid   nvarchar2(40),    --这里从varchar2改成nvarchar2类型才能跟Java的String匹配上  
  focuseduserid nvarchar2(40),
  focusrecord nvarchar2(4)    
)
---多次测试创建可能会出现“ORA-02303: 无法使用类型或表的相关性来删除或取代一个类型”这个错误，这时只要换一个类型名字再创建就可以了
----------
CREATE OR REPLACE TYPE tp_arr_focus_tbl AS TABLE OF tp_arr_focus
--创建临时表
CREATE TABLE tb_temp_profocus( 
	focususerid varchar2(40),
	focuseduserid varchar2(40) primary key, 
	focusrecord varchar2(4)
)
--------------创建包，创建一个游标类型用来放输出参数  
create or replace package focus_pkg as 
 type focus_cur is ref cursor;
 end focus_pkg;
------创建存储过程 ，定义两个参数，一个入参，是一个对象类型数组（这种类型应该可以满足大部分复杂需求了），
-----一个出参，是用游标存放查询值 
 CREATE OR REPLACE  procedure findFocusRecord(type_obj IN tp_arr_focus_tbl,result out focus_pkg.focus_cur)  
  as 
  t tp_arr_focus; 
  sql2 varchar2(500);
  sql3 varchar2(500); 
  v_count varchar2(4);        --临时中间变量，用来存放对应的record 
 BEGIN      
    sql2 :='drop table tb_temp_profocus';
     sql3 :='CREATE TABLE tb_temp_profocus( focususerid varchar2(40),focuseduserid varchar2(40) primary key, focusrecord varchar2(4))';
	   execute immediate sql2;
       execute immediate sql3;
       
       FOR i IN type_obj.first()..type_obj.last() 
       LOOP
       t:= type_obj(i);
       select count(*) into v_count from scott.tb_focus_rel where focususerid=t.focususerid and focuseduserid=t.focuseduserid;
       dbms_output.put_line( t.focususerid || '=>'||t.focuseduserid ||'=>' || v_count);
       insert into tb_temp_profocus values (t.focususerid,t.focuseduserid, v_count);
      END LOOP;
      COMMIT;
      
      open result for select * from tb_temp_profocus;
 END;

 
