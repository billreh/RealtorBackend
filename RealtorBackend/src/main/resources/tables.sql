DROP TABLE address IF EXISTS;

CREATE TABLE address (
	id BIGINT NOT NULL AUTO_INCREMENT,
	street varchar(50),
	city varchar(30),
	state char(2),
	zip_code char(5),
	PRIMARY KEY (id)
);

DROP TABLE agent IF EXISTS;

CREATE TABLE agent (
	id BIGINT NOT NULL AUTO_INCREMENT,
	first_name varchar(20),
	last_name varchar(20),
	contact_number varchar(15),
	email varchar(30),
	PRIMARY KEY(id)
);

DROP TABLE listing IF EXISTS;

CREATE TABLE listing (
	id BIGINT NOT NULL AUTO_INCREMENT,
	address_id BIGINT NOT NULL,
	agent_id BIGINT,
	price INT,
	house_type varchar(30),
	bathrooms DECIMAL(2,1),
	bedrooms INT,
	square_feet INT,
	main_photo varchar(100),
	PRIMARY KEY(id),
	FOREIGN KEY (address_id) REFERENCES address(id),
	FOREIGN KEY (agent_id) REFERENCES agent(id)
);

create table photo (
	id bigint not null auto_increment,
	listing_id bigint,
	name varchar(100),
	primary key(id),
	FOREIGN KEY (listing_id) REFERENCES listing(id)
);

create table listing_detail (
	id bigint not null auto_increment,
	listing_id bigint,
	overview varchar(3000),
	master_bedroom varchar(255),
	full_bathrooms int,
	half_bathrooms int,
	dining_kitchen boolean,
	dining_room boolean,
	stories int,
	exterior varchar(50),
	parking varchar(50),
	status varchar(20),
	school_district varchar(50),
	style varchar(50),
	year_built int,
	primary key(id),
	foreign key (listing_id) references listing(id)
);

create table exterior_feature (
	id bigint not null auto_increment,
	listing_detail_id bigint,
	name varchar(50),
	primary key(id),
	foreign key(listing_detail_id) references listing_detail(id)
);

create table other_room (
	id bigint not null auto_increment,
	listing_detail_id bigint,
	name varchar(50),
	primary key(id),
	foreign key(listing_detail_id) references listing_detail(id)
);

create table featured_listing (
	id bigint not null auto_increment,
	listing_id bigint,
	primary key(id),
	FOREIGN KEY (listing_id) REFERENCES listing(id)
);

insert into address values(NULL, '534 Queen St.', 'Philadelphia', 'PA', '19147');
insert into agent values(NULL, 'Bob', 'Parker', '(215)555-1212', 'bparker@gmail.com');
insert into listing values(NULL, 1, 1, 500000, 'Single Family Home', 2.5, 3, 1732);