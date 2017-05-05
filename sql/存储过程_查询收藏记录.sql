 drop type tp_arr_collect_tbl
 drop type tp_arr_collect
 drop table tb_temp_collect
 drop package collect_pkg
 drop procedure findCollectRecord
 
------------在数据库建立一个type,对应JAVA端要传入的对象结构 :  
create or replace type tp_arr_collect as Object    
(    
  userid   nvarchar2(40),    --这里从varchar2改成nvarchar2类型才能跟Java的String匹配上  
  topicid nvarchar2(40),
  record nvarchar2(4)    
)
---多次测试创建可能会出现“ORA-02303: 无法使用1类型或表的相关性来删除或取代一个类型”这个错误，这时只要换一个类型名字再创建就可以了
----------
CREATE OR REPLACE TYPE tp_arr_collect_tbl AS TABLE OF tp_arr_collect
--创建临时表
CREATE TABLE tb_temp( userid varchar2(40),topicid varchar2(40) primary key, record varchar2(4))
--------------创建包，创建一个游标类型用来放输出参数  
create or replace package collect_pkg as 
 type collect_cur is ref cursor;
 end collect_pkg;
------创建存储过程 ，定义两个参数，一个入参，是一个对象类型数组（这种类型应该可以满足大部分复杂需求了），
-----一个出参，是用游标存放查询值 
 CREATE OR REPLACE  procedure findCollectRecord(type_obj IN tp_arr_collect_tbl,result out collect_pkg.collect_cur)  
  as 
  t tp_arr_collect; 
  sql2 varchar2(500);
  sql3 varchar2(500); 
  v_count varchar2(4);        --临时中间变量，用来存放对应的record 
 BEGIN   
    sql2 :='drop table tb_temp';
     sql3 :='CREATE TABLE tb_temp( userid varchar2(40),topicid varchar2(40) primary key, record varchar2(4))';
	   execute immediate sql2;
       execute immediate sql3;
       
       FOR i IN type_obj.first()..type_obj.last() 
       LOOP
       t:= type_obj(i);
       select count(*) into v_count from scott.tb_collect where userid=t.userid and topicid=t.topicid;
       dbms_output.put_line( t.userid || '=>'||t.topicid ||'=>' || v_count);
       insert into tb_temp values (t.userid,t.topicid, v_count);
      END LOOP;
      COMMIT;
      
      open result for select * from tb_temp;
 END;

 

 
 
 
