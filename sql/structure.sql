
------------------------------------------------
  --          数据结构表
------------------------------------------------
drop table tb_collect;
drop table tb_reply;
drop table tb_focus_rel;
drop table tb_praise_rel;
drop table tb_topic;
drop table tb_pic;
drop table tb_user;
drop table tb_admin;

--管理员表
create table tb_admin(
	id varchar2(50) primary key,
	adminname varchar2(50),
	adminpwd varchar2(50),
	status varchar2(50)
);
select * from tb_admin;

--用户信息表 
create table tb_user(
       id varchar2(40) primary key,     
       email varchar2(40),           --账户 默认为邮箱 
       pwd varchar2(40),                --密码 
       nickname varchar2(40),           --名称 
       autograph varchar2(1000),        --个人签名 
       head varchar2(256),              --头像
       regtime date,                    --注册时间 
       token varchar2(50),              --账号激活码
       token_exptime varchar2(50),      --激活码有效期 
       status int,                      --激活状态 ，0-未激活，1-已激活 
       remarks1 varchar2(3000),         --备用字段 
       islogined int    				--是否已登录
);

select * from tb_user;
update tb_user set islogined=0 where id='1';
select * from tb_user where id not in('1')
delete from tb_user where email='572480349@qq.com'
update tb_user set head='1.jpg' where id='1';
update tb_user set head='2.jpg' where id='2';
select a.*,rownum as rn from (select * from tb_user)a where rownum<=3

--话题表 
create table tb_topic(
       id varchar2(40) primary key,
       userid varchar2(40),             --用户id 
       title varchar2(100),        --标题
       content varchar2(1000),          --内容描述 
       picid varchar2(700),        --图片id
       posttime date,                   --发布时间 
       topictype varchar2(20),      --话题类型 如， 文字、图片、音乐、视频
       remarks1 varchar2(3000),         --备用字段 
       remarks2 varchar2(3000),
       remarks3 varchar2(3000)  
);

select * from tb_topic;
delete from tb_topic where title in ('i am superman')  
update tb_topic set picid='images/888.jpg' where picid='888' 

--图片表
create table tb_pic(
        id varchar2(700) primary key,
        picname varchar2(700),            --图片名
        remarks1 varchar2(3000),        --备用字段 
        remarks2 varchar2(3000),
        remarks3 varchar2(3000)
);
select * from tb_pic where id='http://localhost:8080/lofter/images/2cfd6576-6ba0-41d9-88f2-833451878754.jpg';
select count(*) from tb_pic where id='http://localhost:8080/lofter/images/25150094-fb7f-4064-a3f0-60f875445bbb.jpg'

--点赞关系表 
create table tb_praise_rel(
       id varchar2(40) primary key,
       userid varchar2(40),             --用户id 
       topicid  varchar2(40),            --话题id 
       remarks1 varchar2(3000),        --备用字段 
       remarks2 varchar2(3000),
       remarks3 varchar2(3000)
);

select * from tb_praise_rel;
delete from tb_praise_rel where id='80c49881-5ae3-4e06-86d9-730e5ad7f61b'

select * from tb_user where id in(
select userid  from tb_topic where id in(
select A.* from (select * from  
(select max(topicid) hot from tb_praise_rel group by 
topicid order by max(topicid) desc) a)A where  rownum<=5
))

--关注/被关注关系表 
create table tb_focus_rel(
       focususerid varchar2(40),             --关注方id 
       focuseduserid  varchar2(40),            --被关注方id 
       focustime date ,                          --关注时间
       remarks1 varchar2(3000),        --备用字段 
       remarks2 varchar2(3000),
       remarks3 varchar2(3000)
);

select * from tb_focus_rel;
insert into tb_focus_rel(focususerid,focuseduserid,focustime) values('2','1',sysdate);
select * from tb_focus_rel where focuseduserid='1'
delete from tb_focus_rel where focususerid='8ce76b4b-e845-411f-8cb0-16e7ac66c368'
select * from tb_focus_rel left join tb_user on tb_focus_rel.focususerid=tb_user.id where focuseduserid='1'

alter table tb_focus_rel 
 add constraint pk_focus
   primary key(focususerid,focuseduserid)
   
select tb_user.* from tb_focus_rel left join tb_user on tb_focus_rel.focuseduserid=tb_user.id where focususerid='3'   

select * from tb_focus_rel;

-- 评论回复表 
create table tb_reply(
       id varchar2(40) primary key,        
       topicid  varchar2(40),           --回复的话题id 
       userid varchar2(40),             --回复人id 
       content varchar2(1000),          --回复内容
       parentid  varchar2(40),          --父回复id 不能成为外键
       replytime date,                  --回复时间
       remarks1 varchar2(3000),         --备用字段 
       remarks2 varchar2(3000),
       remarks3 varchar2(3000) 
);

select * from tb_reply;
select * from tb_reply left join tb_user on tb_reply.userid=tb_user.id where topicid='2';
select userid,head,nickname,content,replytime,topicid from tb_reply left join tb_user on tb_reply.userid=tb_user.id where tb_reply.id='1'
delete from tb_reply where userid='1'
select * from tb_reply where topicid='4'

--收藏表 
create table tb_collect(
      id varchar2(40) primary key,
      userid varchar2(40),             --收藏人 
      topicid  varchar2(40),           --收藏的话题id
      collecttime date,                 --收藏时间
      remarks1 varchar2(3000),      --备用字段 
      remarks2 varchar2(3000),
      remarks3 varchar2(3000)
);

select * from tb_collect where userid='1';

select * from tb_topic where id in(select topicid from tb_collect where userid='1');
---外键约束关系 

create table tb_chat(
	id varchar2(40) primary key,
	email varchar2(40),
	chattopic  varchar2(40),
	category   varchar2(40),
)
select * from tb_chat;


select * from tb_chat where chattopic like '%是%'
delete from tb_chat where chattopic='山东人'










