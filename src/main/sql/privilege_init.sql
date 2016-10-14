
INSERT INTO privilege
(parent_id, p_name, p_description, p_url, icon)
VALUES
(-1, '系统管理', '岗位,部门,用户,权限管理', null, null ),
(-1, '岗位管理', '岗位管理', null, null ),
(-1, '部门管理', '部门管理', null, null ),
(-1, '用户管理', '用户管理', null, null ),

(-1, '增加岗位', null, null, null ),
(-1, '删除岗位', null, null, null ),
(-1, '修改岗位', null, null, null ),
(-1, '查询岗位', null, null, null ),

(-1, '增加部门', null, null, null ),
(-1, '删除部门', null, null, null ),
(-1, '修改部门', null, null, null ),
(-1, '查询部门', null, null, null ),

(-1, '增加用户', null, null, null ),
(-1, '删除用户', null, null, null ),
(-1, '修改用户', null, null, null ),
(-1, '查询用户', null, null, null );

update privilege
set
parent_id = 0
where p_id = 1;

update privilege
set
parent_id = 1
where p_id
IN
(2,3,4);

update privilege
set
parent_id = 2
where p_id
IN
(5,6,7,8);

update privilege
set
parent_id = 3
where p_id
IN
(9,10,11,12);

update privilege
set
parent_id = 4
where p_id
IN
(13,14,15,16);

INSERT INTO privilege
(parent_id, p_name, p_description, p_url, icon)
VALUES
(-1, '审批流转管理', '审批流转管理', null, null ),
(-1, '审批流转管理', '审批流转管理', null, null ),
(-1, '申请模板管理', '申请模板管理', null, null ),
(-1, '起草申请', '起草申请', null, null ),
(-1, '待我审查', null, null, null ),
(-1, '我的审批查询', null, null, null ),

(-1, '网上交流', null, null, null ),
(-1, '论坛管理', null, null, null );

delete from privilege where p_id=18;

update privilege
set
parent_id = 0
where p_id
IN
(17, 23);

update privilege
set
parent_id = 17
where p_id
IN
(19,20,21,22);
update privilege
set
parent_id = 17
where p_id=24;

//2016/10//1日新增
//为privilege增加is_menu字段, 为1表示是菜单显示,
alter table `privilege` add column `is_menu` int(1) null default '0' after `icon`;
update table `privilege` set is_menu =1;
update `privilege` set is_menu=0 where p_id  >= 5 and p_id <= 16;

update privilege set p_url='/role/list' where p_id=2;
update privilege set p_url='/department/list' where p_id=3;
update privilege set p_url='/user/list' where p_id=4;

//2016/10/13日新增
//为菜单增加显示级别, 总共就2层级别, 级别存到数据库中,在代码层面就可以减少工作量了
update privilege set is_menu=2 where p_id in (2,3,4,19,20,21,22,24);