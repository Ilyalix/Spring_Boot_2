create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )

create table messages
id bigint not null,
tag varchar(255),
text varchar(255),
user_name bigint,
primary key (id)) engine=MyISAM

create table user
(id bigint not null,
active bit not null,
password varchar(255) not null,
username varchar(255) not null,
primary key (id)) engine=MyISAM

create table user_role
(user_id bigint not null,
roles varchar(255))
engine=MyISAM

alter table messages add constraint FKbo3yqsgqk5fk5trqluqpi4ob4 foreign key (user_name) references user (id)
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id)