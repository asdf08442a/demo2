-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  `name` varchar(64) NOT NULL COMMENT '权限名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '权限描述',
  `url` varchar(256) NOT NULL DEFAULT '' COMMENT '权限访问路径',
  `perms` varchar(256) NOT NULL DEFAULT '' COMMENT '权限标识',
  `parent_id` varchar(32) NOT NULL COMMENT '父级权限id',
  `type` tinyint unsigned NOT NULL COMMENT '类型 0：目录 1：菜单 2：按钮',
  `order_num` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(64) NOT NULL DEFAULT '' COMMENT '图标',
  `status` tinyint unsigned NOT NULL DEFAULT 1 COMMENT '状态：1有效；2删除',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('1', '工作台', '工作台', '/workdest', 'workdest', '0', '1', '1', 'fa fa-home', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('2', '权限管理', '权限管理', '', '', '0', '0', '2', 'fa fa-th-list', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('201', '用户管理', '用户管理', '/users', 'users', '2', '1', '1', 'fa fa-circle-o', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20101', '列表查询', '用户列表查询', '/user/list', 'user:list', '3', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20102', '新增', '新增用户', '/user/add', 'user:add', '3', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20103', '编辑', '编辑用户', '/user/edit', 'user:edit', '3', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20104', '删除', '删除用户', '/user/delete', 'user:delete', '3', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20105', '批量删除', '批量删除用户', '/user/batch/delete', 'user:batchDelete', '3', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20106', '分配角色', '分配角色', '/user/assign/role', 'user:assignRole', '3', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('202', '角色管理', '角色管理', '/roles', 'roles', '2', '1', '2', 'fa fa-circle-o', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20201', '列表查询', '角色列表查询', '/role/list', 'role:list', '10', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20202', '新增', '新增角色', '/role/add', 'role:add', '10', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20203', '编辑', '编辑角色', '/role/edit', 'role:edit', '10', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20204', '删除', '删除角色', '/role/delete', 'role:delete', '10', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20205', '批量删除', '批量删除角色', '/role/batch/delete', 'role:batchDelete', '10', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20206', '分配权限', '分配权限', '/role/assign/permission', 'role:assignPerms', '10', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('203', '资源管理', '资源管理', '/permissions', 'permissions', '2', '1', '3', 'fa fa-circle-o', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20301', '列表查询', '资源列表', '/permission/list', 'permission:list', '17', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20302', '新增', '新增资源', '/permission/add', 'permission:add', '17', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20303', '编辑', '编辑资源', '/permission/edit', 'permission:edit', '17', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('20304', '删除', '删除资源', '/permission/delete', 'permission:delete', '17', '2', '0', '', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('3', '运维管理', '运维管理', '', '', '0', '0', '3', 'fa fa-th-list', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('301', '数据监控', '数据监控', '/database/monitoring', 'database', '22', '1', '1', 'fa fa-circle-o', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('4', '系统工具', '系统工具', '', '', '0', '0', '4', 'fa fa-th-list', '1');
INSERT INTO `permission`(permission_id, name, description, url, perms, parent_id, type, order_num, icon, status) VALUES ('401', '图标工具', '图标工具', '/icons', 'icons', '24', '1', '1', 'fa fa-circle-o', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '角色描述',
  `status` tinyint unsigned NOT NULL DEFAULT 1 COMMENT '状态：1有效；2删除',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`(role_id, name, description, status) VALUES ('1', '超级管理员', '超级管理员', '1');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `permission_id` varchar(32) NOT NULL COMMENT '权限id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '1');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '2');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '201');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20101');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20102');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20103');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20104');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20105');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20106');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '202');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20201');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20202');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20203');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20204');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20205');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20206');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '203');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20301');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20302');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20303');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '20304');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '3');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '301');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '4');
INSERT INTO `role_permission`(role_id, permission_id) VALUES ('1', '401');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(128) NOT NULL COMMENT '加密盐值',
  `email` varchar(32) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(32) NOT NULL DEFAULT '' COMMENT '联系方式',
  `sex` tinyint unsigned NOT NULL COMMENT '性别：1男，2女',
  `age` tinyint unsigned NOT NULL COMMENT '年龄',
  `status` tinyint unsigned NOT NULL DEFAULT 1 COMMENT '用户状态：1有效; 2删除',
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`(user_id, username, password, salt, email, phone, sex, age, status)
VALUES ('1', 'admin', '872359cc44c637cc73b7cd55c06d95e4', '8cd50474d2a3c1e88298e91df8bf6f1c', '523179414@qq.com', '187888899991', '1', '20', '1');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`(user_id, role_id) VALUES ('1', '1');