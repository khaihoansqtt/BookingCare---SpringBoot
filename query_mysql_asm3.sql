create database prj321x_asm3;

create table users(
	id int not null primary key auto_increment,
    email varchar(255) not null unique,
    password varchar(255) not null,
	name varchar(255) not null,
	address varchar(255),
	phone varchar(255),
	avatar varchar(255),
    gender varchar(255),
	description varchar(255),
	is_active int,
	role_id int not null,
    block_reason varchar(255),
    created_at datetime not null default now(),
    updated_at datetime not null default now() on update now(),
    foreign key (role_id) references roles(id));
insert into users values (1, "khaihoancr7@gmail.com", "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu", "khai hoan", "song binh",
"0396826222", "avatar", "male", "description", 1, 1, null, now(), now());

create table roles(
	id int not null primary key auto_increment,
    name varchar(255) not null);
insert into roles values (1, "ROLE_ADMIN"), (2, "ROLE_DOCTOR"), (3, "ROLE_PATIENT");

create table doctors(
	id int not null primary key auto_increment,
    user_id int not null,
    clinic_id int not null,
    specialty_id int not null,
    info text,
    training text,
    achievement text,
    price varchar(255),
    created_at datetime not null default now(),
    update_at datetime not null default now() on update now(),
    foreign key (doctor_id) references users(id),
    foreign key (clinic_id) references clinics(id),
    foreign key (specialty_id) references specialties(id));
    
create table specialties(
	id int not null primary key auto_increment,
    name varchar(255) not null,
    description text,
    image varchar(255),
    created_at datetime not null default now(),
    update_at datetime not null default now() on update now());
insert into specialties values (1, "Cơ xương khớp", "đây là chuyên môn cơ xương khớp", "image", now(), now()),
(2, "Thần kinh", "đây là chuyên môn thần kinh", "image", now(), now()),
(3, "Tiêu hóa", "đây là chuyên môn tiêu hóa", "image", now(), now()),
(4, "Tâm lí", "đây là chuyên môn cơ tâm lí", "image", now(), now()),
(5, "Vật lí trị liệu", "đây là chuyên môn vật lí trị liệu", "image", now(), now());
    
create table clinics(
	id int not null primary key auto_increment,
    name varchar(255) not null,
    address varchar(255) not null,
    phone varchar(255) not null,
    introduction_html text,
    introduction_markdown text,
    description text,
    image varchar(255),
    created_at datetime not null default now(),
    update_at datetime not null default now() on update now());
insert into clinics values (1, "Bệnh viện Việt Đức", "Hà Nội", "098832132", "html", "markdown", "đây là bệnh viện xịn", "image", now(), now()),
(2, "Bệnh viện Chợ Rẫy", "Sài Gòn", "098832132", "html", "markdown", "đây là bệnh viện xịn", "image", now(), now()),
(3, "Bệnh viện Bạch Mai", "Hà Nội", "098832132", "html", "markdown", "đây là bệnh viện xịn", "image", now(), now()),
(4, "Bệnh viện Quân Y 108", "Hà Nội", "098832132", "html", "markdown", "đây là bệnh viện xịn", "image", now(), now()),
(5, "Bệnh viện Quân Y 175", "Sài Gòn", "098832132", "html", "markdown", "đây là bệnh viện xịn", "image", now(), now());

create table schedules (
	id int not null primary key auto_increment,
    time varchar(255));
insert into schedules values (1, "06.30 - 07.30"), (2, "07.30 - 08.30"), (3, "08.30 - 09.30"), (4, "09.30 - 10.30"), (5, "14.00 - 15.00"), 
(6, "15.00 - 16.00"), (7, "16.00 - 17.00"), (8, "19.00 - 20.00"), (9, "20.00 - 21.00");

create table doctor_schedule(
	id int not null primary key auto_increment,
    doctor_id int not null,
    schedule_id int not null,
    foreign key (doctor_id) references doctors(id),
    foreign key (schedule_id) references schedules(id));

create table categories(
	id int not null primary key auto_increment,
    name varchar(255));
insert into categories values (1, "đau chân"), (2, "đau đầu"), (3, "đau thận"), (4, "đau cơ"), (5, "đau bụng");

create table doctor_category(
	id int not null primary key auto_increment,
    doctor_id int not null,
    category_id int not null,
    foreign key (doctor_id) references doctors(id),
    foreign key (category_id) references categories(id));

create table medical_appointments(
	id int not null primary key auto_increment,
    doctor_id int not null,
    patient_id int not null,
    time varchar(255),
    price varchar(255),
    name varchar(255),
    gender varchar(255),
    phone varchar(255),
    address varchar(255),
    reason varchar(255),
    is_confirmed int,
    cancel_reason varchar(255),
    foreign key (doctor_id) references doctors(id),
    foreign key (patient_id) references users(id));

create table history_appointments(
	id int not null primary key auto_increment,
    medical_appointment_id int,
    basic_pathological varchar(255),
    pathological_details text,
    foreign key (medical_appointment_id) references medical_appointments(id));