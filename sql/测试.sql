 drop type tp_arr_tbl5
 drop type tp_arr5
 drop table tb_temp5
 drop package testpkg
 drop procedure findRecord
 
create or replace type tp_arr5 as Object    
(    
  userid   nvarchar2(40),   
  topicid nvarchar2(40),
  record nvarchar2(4)    
)


CREATE OR REPLACE TYPE tp_arr_tbl5 AS TABLE OF tp_arr5;


--创建临时表
CREATE TABLE tb_temp5( userid varchar2(40),topicid varchar2(40) primary key, record varchar2(4));

--------------创建包，创建一个游标类型用来放输出参数  
create or replace package testpkg as 
 type testcur is ref cursor 
 end testpkg;
 
------创建存储过程 ，定义两个参数，一个入参，是一个对象类型数组（这种类型应该可以满足大部分复杂需求了），
-----一个出参，是用游标存放查询值 

 CREATE OR REPLACE  procedure findRecord(type_obj IN tp_arr_tbl5,result out testpkg.testcur)  
  as 
  t tp_arr5 
  sql2 varchar2(500)
  sql3 varchar2(500)
  v_count varchar2(4)       --临时中间变量，用来存放对应的record 
 BEGIN     
	 sql2 :='drop table tb_temp5'
     sql3 :='CREATE TABLE tb_temp5( userid varchar2(40),topicid varchar2(40) primary key, record varchar2(4))'
       execute immediate sql2
       execute immediate sql3
    FOR i IN type_obj.first()..type_obj.last() 
       LOOP
       t:= type_obj(i) 
       select count(*) into v_count from scott.tb_praise_rel where userid=t.userid and topicid=t.topicid 
       insert into tb_temp5 values (t.userid,t.topicid, v_count) 
       END LOOP 
       COMMIT 
       open result for select * from tb_temp5
       END;

       
       