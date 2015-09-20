##角色初始化
DELETE FROM role;
INSERT INTO role VALUES('f3f0228d-c7d4-4f39-96f8-3cb4969787ae','注册用户','普通用户角色'),('420679ef-7333-4f96-aaaf-dc8ed5564122','管理员','管理员角色');

## 修改space数据库名为spaces，增加场地级别字段
alter table space rename spaces;
alter table spaces add space_level TINYINT NOT NULL COMMENT '场地级别：0:普通场地，1:精品场地';

## 为用户表中的mdn列创建唯一索引
CREATE UNIQUE INDEX mdn_index ON USER(mdn);