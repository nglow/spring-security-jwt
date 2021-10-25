drop table if exists user_authority;

drop table if exists authority;

drop table if exists user;

create table authority
(
    authority_id   bigint auto_increment
        primary key,
    authority_name varchar(50) null
);

create table user
(
    user_id  bigint auto_increment
        primary key,
    nickname varchar(50)  null,
    password varchar(100) null,
    state    varchar(255) null,
    username varchar(50)  null,
    constraint UK_sb8bbouer5wak8vyiiy4pf2bx
        unique (username)
);

create table user_authority
(
    user_authority_id bigint auto_increment
        primary key,
    authority_id      bigint null,
    user_id           bigint null,
    constraint FKgvxjs381k6f48d5d2yi11uh89
        foreign key (authority_id) references authority (authority_id),
    constraint FKpqlsjpkybgos9w2svcri7j8xy
        foreign key (user_id) references user (user_id)
);

