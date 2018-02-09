
DROP DATABASE IF EXISTS cosdb;
CREATE DATABASE cosdb CHARACTER SET utf8;

USE cosdb;

/*1删除角色权限组关系表*/
DROP TABLE  IF EXISTS role_groups;
/*2删除公告阅读记录*/
DROP TABLE  IF EXISTS member_notice;
/*3删除公告表*/
DROP TABLE  IF EXISTS notice;
/*4删除任务表*/
DROP TABLE  IF EXISTS task;
/*5删除文档表*/
DROP TABLE  IF EXISTS document;
/*6删除项目表*/
DROP TABLE  IF EXISTS project;
/*7删除用户表*/
DROP TABLE  IF EXISTS member;
/*8文档类型*/
DROP TABLE  IF EXISTS doctype;
/*9删除角色表*/
DROP TABLE  IF EXISTS role;
/*10删除权限表*/
DROP  TABLE IF EXISTS actions;
/*11删除权限组表*/
DROP TABLE   IF EXISTS groups;

/*12删除任务类型表*/
DROP TABLE   IF EXISTS tasktype;

/*创建角色表*/
CREATE TABLE role(
 
 rid        INT    AUTO_INCREMENT,
 title      VARCHAR(50)          ,
 note       TEXT                 ,
 
 CONSTRAINT pk_mid  PRIMARY KEY(rid)
) engine=InnoDB; 

 /*创建权限组表*/
CREATE TABLE groups(
  
  gid       INT    AUTO_INCREMENT,
  title     VARCHAR(50)          ,
  note      TEXT                 ,
  CONSTRAINT pk_gid  PRIMARY KEY(gid)

)engine=InnoDB;

/*创建权限表*/
CREATE TABLE actions(
  actid        INT   AUTO_INCREMENT,
  title		   VARCHAR(50)	NOT NULL ,
  url		   VARCHAR(200)	NOT NULL ,
  gid		   INT ,
  
  CONSTRAINT pk_actid PRIMARY KEY(actid) ,
  CONSTRAINT fk_gid   FOREIGN KEY(gid) REFERENCES groups(gid) ON DELETE CASCADE

)engine=InnoDB;

/*创建角色权限组关系表*/
CREATE TABLE role_groups(
  rid        INT,
  gid        INT,
  
  CONSTRAINT  fk_rid1 FOREIGN KEY(rid) REFERENCES role(rid) ON DELETE CASCADE,
  CONSTRAINT  fk_gid1 FOREIGN KEY(gid) REFERENCES groups(gid) ON DELETE CASCADE
)engine=InnoDB;

/* 创建用户表*/
CREATE TABLE member (
	mid				    VARCHAR(50) ,
	password			VARCHAR(32)	NOT NULL ,
	name				VARCHAR(50)	NOT NULL ,
	level				INT		NOT NULL ,
	phone				VARCHAR(50),
	email				VARCHAR(50),
	photo				VARCHAR(200)	DEFAULT 'nophoto.jpg',
	rid					INT,
	lastlogin			DATETIME,
	lockflag			INT,
	CONSTRAINT pk_mid PRIMARY KEY (mid) ,
	CONSTRAINT fk_rid2 FOREIGN KEY(rid) REFERENCES role(rid) ON DELETE SET NULL
) engine=innodb ;

-- 创建公告表
CREATE TABLE notice (
	snid				INT	AUTO_INCREMENT ,
	mid				    VARCHAR(50),
	title				VARCHAR(50),
	pubdate				DATETIME,
	content				TEXT,
	level				INT,
	rnum				INT	,
	CONSTRAINT pk_snid PRIMARY KEY(snid) ,
	CONSTRAINT fk_mid1 FOREIGN KEY(mid) REFERENCES member(mid)
) engine=innoDB ;

/*创建用户公告阅读记录信息表*/
CREATE TABLE member_notice(
 mid          VARCHAR(50) ,
 snid         INT,
 rdate        DATETIME,
 
 CONSTRAINT fk_mid2  FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE,
 CONSTRAINT fk_snid  FOREIGN KEY(snid) REFERENCES notice(snid) ON DELETE CASCADE

)engine=InnoDB;


/*创建文档类型表*/
CREATE TABLE doctype (
	dtid				INT 	AUTO_INCREMENT ,
	title				VARCHAR(50)	NOT NULL ,
	CONSTRAINT pk_dtid PRIMARY KEY (dtid)
) engine=innodb ;

-- 创建文档表
CREATE TABLE document (
	did					INT 	AUTO_INCREMENT ,
	mid				VARCHAR(50),
	dtid				INT,
	title				VARCHAR(50)	NOT NULL ,
	content				TEXT,
	file				VARCHAR(200),
	pubdate				DATETIME,
	CONSTRAINT pk_did PRIMARY KEY (did) ,
	CONSTRAINT fk_dtid FOREIGN KEY(dtid) REFERENCES doctype(dtid) ,
	CONSTRAINT fk_mid FOREIGN KEY(mid) REFERENCES member(mid)
) engine=innodb ;

/*创建项目表*/
CREATE TABLE project (
	proid				INT 	AUTO_INCREMENT ,
	creid				VARCHAR(50),
	title				VARCHAR(50),
	mgr					VARCHAR(50),
	name				VARCHAR(50),
	note				TEXT,
	pubdate				DATETIME,
	CONSTRAINT pk_proid PRIMARY KEY(proid) ,
	CONSTRAINT fk_creid FOREIGN KEY(creid) REFERENCES member(mid) ON DELETE SET NULL ,
	CONSTRAINT fk_mgr FOREIGN KEY(mgr) REFERENCES member(mid) ON DELETE SET NULL
) engine=innodb ;

/*创建任务类型表*/
CREATE TABLE tasktype (
	ttid				INT  	AUTO_INCREMENT ,
	title				VARCHAR(50)	NOT NULL ,
	CONSTRAINT pk_ttid PRIMARY KEY(ttid)
) engine=innodb ;

/* 创建任务表*/
CREATE TABLE task (
	tid				INT 	AUTO_INCREMENT ,
	creator				VARCHAR(50),
	receiver			VARCHAR(50),
	canceler			VARCHAR(50),
	ttid				INT,
	proid				INT,
	title				VARCHAR(50),
	expiredate			DATETIME,
	createdate			DATETIME,
	finishdate 			DATETIME,
	status				INT,
	lastupdatedate		DATETIME ,
	priority			INT ,
	expend				INT,
	estimate			INT,
	note				TEXT,
	rnote				TEXT,
	cnote				TEXT,
	CONSTRAINT pk_tid PRIMARY KEY(tid) ,
	CONSTRAINT fk_creator FOREIGN KEY(creator) REFERENCES member(mid) ON DELETE SET NULL ,
	CONSTRAINT fk_receiver FOREIGN KEY(receiver) REFERENCES member(mid) ON DELETE SET NULL ,
	CONSTRAINT fk_canceler FOREIGN KEY(canceler) REFERENCES member(mid) ON DELETE SET NULL ,
	CONSTRAINT fk_ttid FOREIGN KEY(ttid) REFERENCES tasktype(ttid) ON DELETE SET NULL ,
	CONSTRAINT fk_proid FOREIGN KEY(proid) REFERENCES project(proid) ON DELETE CASCADE
) engine=innodb ;

/*测试数据 —— 角色信息*/
INSERT INTO role(title,note) VALUES ('超级管理员','-') ;
INSERT INTO role(title,note) VALUES ('普通管理员','-') ;
INSERT INTO role(title,note) VALUES ('信息管理员','-') ;
INSERT INTO role(title,note) VALUES ('项目经理','-') ;
INSERT INTO role(title,note) VALUES ('普通员工','-') ;

/* 测试数据 —— 用户表（密码：hello）*/
INSERT INTO member(mid,name,password,level,photo,rid) VALUES ('admin','超级管理员','5D41402ABC4B2A76B9719D911017C592',0,'nophoto.jpg',1) ;
INSERT INTO member(mid,name,password,level,photo,rid) VALUES ('root','普通管理员','5D41402ABC4B2A76B9719D911017C592',1,'nophoto.jpg',2) ;
INSERT INTO member(mid,name,password,level,photo,rid) VALUES ('info','信息管理员','5D41402ABC4B2A76B9719D911017C592',1,'nophoto.jpg',3) ;
INSERT INTO member(mid,name,password,level,photo,rid) VALUES ('manager','MLDN项目经理','5D41402ABC4B2A76B9719D911017C592',2,'nophoto.jpg',4) ;
INSERT INTO member(mid,name,password,level,photo,rid) VALUES ('member','MLDN开发人员','5D41402ABC4B2A76B9719D911017C592',3,'nophoto.jpg',5) ;

-- 测试数据 —— 权限组
INSERT INTO groups(title,note) VALUES ('用户管理','-') ;
INSERT INTO groups(title,note) VALUES ('公告管理','-') ;
INSERT INTO groups(title,note) VALUES ('项目管理','-') ;
INSERT INTO groups(title,note) VALUES ('文档管理','-') ;
INSERT INTO groups(title,note) VALUES ('权限管理','-') ;




/*测试数据 —— 管理员-权限组关系*/
INSERT INTO role_groups(rid,gid) VALUES (1,1) ;
INSERT INTO role_groups(rid,gid) VALUES (1,2) ;
INSERT INTO role_groups(rid,gid) VALUES (1,3) ;
INSERT INTO role_groups(rid,gid) VALUES (1,4) ;
INSERT INTO role_groups(rid,gid) VALUES (1,5) ;

/*普通管理员-权限组关系*/
INSERT INTO role_groups(rid,gid) VALUES (2,1) ;
INSERT INTO role_groups(rid,gid) VALUES (2,2) ;
INSERT INTO role_groups(rid,gid) VALUES (2,3) ;
INSERT INTO role_groups(rid,gid) VALUES (2,4) ;

/*--信息 管理员-权限组关系*/
INSERT INTO role_groups(rid,gid) VALUES (3,2) ;
INSERT INTO role_groups(rid,gid) VALUES (3,4) ;

/*---项目经理权限组关系*/
INSERT INTO role_groups(rid,gid) VALUES (4,2) ;
INSERT INTO role_groups(rid,gid) VALUES (4,3) ;
INSERT INTO role_groups(rid,gid) VALUES (4,4) ;

/*--雇员 -权限组关系*/
INSERT INTO role_groups(rid,gid) VALUES (5,2) ;
INSERT INTO role_groups(rid,gid) VALUES (5,3) ;
INSERT INTO role_groups(rid,gid) VALUES (5,4) ;

/*-- 测试数据 —— 管理员权限*/
INSERT INTO actions(title,url,gid) VALUES ('添加用户','/pages/jsp/admin/user/UserActionAdmin!insertPre.action',1) ;
INSERT INTO actions(title,url,gid) VALUES ('活跃用户列表','/pages/jsp/admin/user/UserActionAdmin!listActive.action',1) ;
INSERT INTO actions(title,url,gid) VALUES ('锁定用户列表','/pages/jsp/admin/user/UserActionAdmin!listLock.action',1) ;

INSERT INTO actions(title,url,gid) VALUES ('添加公告','/pages/jsp/admin/notice/NoticeActionAdmin!insertPre.action',2) ;
INSERT INTO actions(title,url,gid) VALUES ('管理公告','/pages/jsp/admin/notice/NoticeActionAdmin!list.action',2) ;

INSERT INTO actions(title,url,gid) VALUES ('发布项目','/pages/jsp/admin/project/ProjectActionAdmin!insertPre.action',3) ;
INSERT INTO actions(title,url,gid) VALUES ('项目管理','/pages/jsp/admin/project/ProjectActionAdmin!list.action',3) ;
INSERT INTO actions(title,url,gid) VALUES ('任务分类维护','/pages/jsp/admin/task/TasktypeActionAdmin!list.action',3) ;
INSERT INTO actions(title,url,gid) VALUES ('上传文档','/pages/jsp/admin/document/DocumentActionAdmin!insertPre.action',4) ;
INSERT INTO actions(title,url,gid) VALUES ('文档管理','/pages/jsp/admin/document/DocumentActionAdmin!list.action',4) ;
INSERT INTO actions(title,url,gid) VALUES ('文档分类维护','/pages/jsp/admin/document/DoctypeActionAdmin!list.action',4) ;
INSERT INTO actions(title,url,gid) VALUES ('管理员管理','/pages/jsp/admin/admin/AdminActionAdmin!list.action',5) ;
INSERT INTO actions(title,url,gid) VALUES ('增加角色','/pages/jsp/admin/role/RoleActionAdmin!insertPre.action',5) ;
INSERT INTO actions(title,url,gid) VALUES ('角色列表','/pages/jsp/admin/role/RoleActionAdmin!list.action',5) ;
INSERT INTO actions(title,url,gid) VALUES ('权限组管理','/pages/jsp/admin/role/GroupsActionAdmin!list.action',5) ;
INSERT INTO actions(title,url,gid) VALUES ('权限管理','/pages/jsp/admin/role/ActionActionAdmin!list.action',5) ;


/*文档类型*/
INSERT INTO doctype(title) VALUES ('Java开发') ;
INSERT INTO doctype(title) VALUES ('WEB前端') ;
INSERT INTO doctype(title) VALUES ('项目资料') ;

/* 任务类型*/
INSERT INTO tasktype(title) VALUES ('需求分析与设计') ;
INSERT INTO tasktype(title) VALUES ('数据库设计') ;
INSERT INTO tasktype(title) VALUES ('编程开发') ;
INSERT INTO tasktype(title) VALUES ('项目整合') ;
INSERT INTO tasktype(title) VALUES ('项目测试') ;




/*文档类型*/
INSERT INTO doctype(title) VALUES ('Java开发') ;
INSERT INTO doctype(title) VALUES ('WEB前端') ;
INSERT INTO doctype(title) VALUES ('项目资料') ;

/* 任务类型*/
INSERT INTO tasktype(title) VALUES ('需求分析与设计') ;
INSERT INTO tasktype(title) VALUES ('数据库设计') ;
INSERT INTO tasktype(title) VALUES ('编程开发') ;
INSERT INTO tasktype(title) VALUES ('项目整合') ;
INSERT INTO tasktype(title) VALUES ('项目测试') ;

