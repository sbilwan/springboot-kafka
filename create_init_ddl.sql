create table item (item_id varchar(255) not null, created_date_time timestamp, updated_date_time timestamp, item_description varchar(255), item_pice numeric(19, 2), manufacturing_date date, order_id varchar(255), primary key (item_id))
create table order (order_id varchar(255) not null, created_date_time timestamp, updated_date_time timestamp, order_price numeric(19, 2), primary key (order_id))
alter table if exists item add constraint FK_ORDER_ID foreign key (order_id) references order
