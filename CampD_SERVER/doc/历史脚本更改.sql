##��ɫ��ʼ��
DELETE FROM role;
INSERT INTO role VALUES('f3f0228d-c7d4-4f39-96f8-3cb4969787ae','ע���û�','��ͨ�û���ɫ'),('420679ef-7333-4f96-aaaf-dc8ed5564122','����Ա','����Ա��ɫ');

## �޸�space���ݿ���Ϊspaces�����ӳ��ؼ����ֶ�
alter table space rename spaces;
alter table spaces add space_level TINYINT NOT NULL COMMENT '���ؼ���0:��ͨ���أ�1:��Ʒ����';

## Ϊ�û����е�mdn�д���Ψһ����
CREATE UNIQUE INDEX mdn_index ON USER(mdn);