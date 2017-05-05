------------------------------------------------------------
--        数据的插入、复杂查询等操作
------------------------------------------------------------
--管理员信息数据
insert into tb_admin(id,adminname,adminpwd,status) values('1','a','0cc175b9c0f1b6a831c399e269772661','on');

--用户信息数据 
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('1','572480349@qq.com','0cc175b9c0f1b6a831c399e269772661','笑看未来','一上数学课，我就知道昨天晚上我没睡好!','user1.jpg',sysdate,1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('2','96095877@qq.com','0cc175b9c0f1b6a831c399e269772661','superman','哥是来拯救世界的','user2.jpg',sysdate,1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('3','15583239990@163.com','0cc175b9c0f1b6a831c399e269772661','hollyhigh','yes!I am legend','user3.jpg',sysdate,1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('4','15583239991@163.com','0cc175b9c0f1b6a831c399e269772661','flyingbird','我觉得我有特殊能力怎么办','user4.jpg',to_date('2015-05-29','yyyy-mm-dd'),1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('5','15583239992@163.com','0cc175b9c0f1b6a831c399e269772661','sweetydog','给我一个理由忘记（王老吉）','user5.jpg',to_date('2015-05-01','yyyy-mm-dd'),1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('6','15583239993@163.com','0cc175b9c0f1b6a831c399e269772661','不做死就不死星人','I remember you','user6.jpg',to_date('2015-04-01','yyyy-mm-dd'),1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('7','15583239994@163.com','0cc175b9c0f1b6a831c399e269772661','铅笔兄','我有一个室友叫铅笔','user7.jpg',to_date('2015-03-12','yyyy-mm-dd'),1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('8','15583239995@163.com','0cc175b9c0f1b6a831c399e269772661','bigbigbang','The big Bang','user8.jpg',to_date('2015-04-19','yyyy-mm-dd'),1,0);   
insert into tb_user(id,email,pwd,nickname,autograph,head,regtime,status,islogined) values('9','15583239996@163.com','0cc175b9c0f1b6a831c399e269772661','范特西','依然范特西','user9.jpg',to_date('2015-05-29','yyyy-mm-dd'),1,0);   

--图片表数据
insert into tb_pic(id,picname) values('images/1.jpg','1.jpg');
insert into tb_pic(id,picname) values('images/2.jpg','2.jpg');
insert into tb_pic(id,picname) values('images/3.jpg','3.jpg');
insert into tb_pic(id,picname) values('images/4.jpg','4.jpg');
insert into tb_pic(id,picname) values('images/5.jpg','5.jpg');
insert into tb_pic(id,picname) values('images/6.jpg','6.jpg');
insert into tb_pic(id,picname) values('images/7.jpg','7.jpg');
insert into tb_pic(id,picname) values('images/8.jpg','8.jpg');
insert into tb_pic(id,picname) values('images/9.jpg','9.jpg');
insert into tb_pic(id,picname) values('images/10.jpg','10.jpg');
insert into tb_pic(id,picname) values('images/11.jpg','11.jpg');
insert into tb_pic(id,picname) values('images/12.jpg','12.jpg');
insert into tb_pic(id,picname) values('images/888.jpg','888.jpg');

--话题数据 
insert into tb_topic(id,userid,title,content,picid,posttime,topictype) values('1','1','风景如画','South Melbourne Beach','images/1.jpg',sysdate,'text');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('2','2','','images/2.jpg',sysdate,'pic');
insert into tb_topic(id,userid,title,content,picid,posttime,topictype) values('3','2','小憩','松间小憩','images/3.jpg',sysdate,'text');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('4','1','速度与拖拉机情！','images/4.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('5','2','乍暖还寒，悠然午后。','images/5.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('6','1','手入室外排队等到睡着的冲田组^q^\n感谢点图的响应，评论都一一看过了，有点被数量吓到OTZ\n应该只能画十张左右这样，形式还没有想好\n感觉得到很多梗并不是一时兴起的呢^^,谢谢','images/6.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('7','2','找个男朋友，要像金大川那样的！','images/7.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('8','1','我喜欢你\n想念当初那样','images/8.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('9','1','很多时候别人说你变了 并不是真的变了 而是你在进步 他们却停滞不前','images/9.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('10','1','老之将至 我一样爱你❤ （出境：姥姥，姥爷）','images/10.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('11','2','《老地方》\n\n第一次来到这儿的我们\n遇到了不知来了多少次的你们\n陪伴是最长情的告白。\n哪片风景会是属于我们的老地方？\n摄于正在闹独立的苏格兰Loch Lomond国家公园 ','images/11.jpg',sysdate,'pic');
insert into tb_topic(id,userid,content,picid,posttime,topictype) values('12','1','','images/12.jpg',sysdate,'pic');

--点赞关系数据 
insert into tb_praise_rel(id,userid,topicid) values('1','1','9');
insert into tb_praise_rel(id,userid,topicid) values('2','1','11');
insert into tb_praise_rel(id,userid,topicid) values('5','2','9');
insert into tb_praise_rel(id,userid,topicid) values('4','1','5');


--关注/被关注数据 
insert into tb_focus_rel(focususerid,focuseduserid,focustime) values('1','2',sysdate);
insert into tb_focus_rel(focususerid,focuseduserid,focustime) values('2','1',sysdate);
insert into tb_focus_rel(focususerid,focuseduserid,focustime) values('3','2',sysdate);
insert into tb_focus_rel(focususerid,focuseduserid,focustime) values('3','1',sysdate);

--评论回复数据 
insert into tb_reply(id,topicid,userid,content,parentid,replytime) values('1','2','1','不错不错','',sysdate);
insert into tb_reply(id,topicid,userid,content,parentid,replytime) values('2','2','1','很好，我喜欢','',sysdate);
insert into tb_reply(id,topicid,userid,content,parentid,replytime) values('3','2','1','赞','',sysdate);
insert into tb_reply(id,topicid,userid,content,parentid,replytime) values('4','1','2','赞','',sysdate);
insert into tb_reply(id,topicid,userid,content,parentid,replytime) values('5','5','2','赞','',sysdate);

--收藏数据 
insert into tb_collect(id,userid,topicid,collecttime) values('1','1','2',sysdate);
insert into tb_collect(id,userid,topicid,collecttime) values('2','1','9',sysdate);

select * from tb_topic;
delete tb_topic where id not in('1','2','3');
select c.id topicid,c.userid,c.title,c.content,c.picid,c.posttime,c.topictype,c.replycount,c.praisecount,email,pwd,nickname,autograph,head,regtime,status from ( select b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount , count( tb_praise_rel.id) as praisecount from( select  a.id,a.userid,title,a.content,picid,posttime,topictype , count(tb_reply.id)as replycount from ( select tb_topic.id as id,tb_topic.userid as userid,title,tb_topic.content as content,picid,posttime,topictype from tb_topic where tb_topic.userid='2')  a left join tb_reply on a.id=tb_reply.topicid group by a.id,a.userid,title,a.content,picid,posttime,topictype) b left join tb_praise_rel on b.id=tb_praise_rel.topicid group by b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount ) c left join tb_user on c.userid=tb_user.id


--根据登录用户id查询其自己和所关注的人的topic
select * from 
(select a.*, rownum as rn from
 (select c.id topicid,c.userid,c.title,c.content,c.picid,c.posttime,c.topictype,c.replycount,c.praisecount,email,pwd,nickname,autograph,head,regtime,status from 
  (select b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount , count( tb_praise_rel.id) as praisecount from
   (select  a.id,a.userid,title,a.content,picid,posttime,topictype , count(tb_reply.id)as replycount from 
    (select tb_topic.id as id,tb_topic.userid as userid,title,tb_topic.content as content,picid,posttime,topictype from tb_topic 
      where tb_topic.userid in
       (select id from tb_user where id in
        (select focuseduserid from tb_focus_rel where focususerid='2') or id='1'
       )
     ) a left join tb_reply on a.id=tb_reply.topicid 
     group by a.id,a.userid,title,a.content,picid,posttime,topictype) b 
     left join tb_praise_rel on b.id=tb_praise_rel.topicid 
     group by b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount 
   ) c left join tb_user on c.userid=tb_user.id
   order by posttime desc) a where rownum<=2) where rn>=1

--用户直接访问show.jsp，按我们推送的内容显示
select c.id topicid,c.userid,c.title,c.content,c.picid,c.posttime,c.topictype,c.replycount,c.praisecount,email,pwd,nickname,autograph,head,regtime,status from 
 ( select b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount , count( tb_praise_rel.id) as praisecount from
    ( select  a.id,a.userid,title,a.content,picid,posttime,topictype , count(tb_reply.id)as replycount from 
     ( select tb_topic.id as id,tb_topic.userid as userid,title,tb_topic.content as content,picid,posttime,topictype from tb_topic 
       where tb_topic.userid in
        ( select id from tb_user where id 
          in(select focuseduserid from tb_focus_rel where focususerid='2') or id='1'
         )
      )  a left join tb_reply on a.id=tb_reply.topicid 
      group by a.id,a.userid,title,a.content,picid,posttime,topictype
    ) b left join tb_praise_rel on b.id=tb_praise_rel.topicid 
      group by b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount 
  ) c left join tb_user on c.userid=tb_user.id order by posttime desc

select * from tb_user;









