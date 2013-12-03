create schema if not exists controle;

create table logradouro (
	id integer not null auto_increment,
	endereco varchar(120),
	numero integer(6),
	complemento varchar(20),
	bairro varchar(30),
	cep varchar(8),
	cidade varchar(30),
	estado varchar(2),
	email varchar(100),
	ddd varchar(3),
	telefone varchar(9),
	primary key(id)
);

create table fornecedor (
	id integer not null auto_increment,
	firma varchar(100),
	comprador varchar(50),
	cnpj varchar(15),
	inscricao varchar(15),
	ativo boolean,
	id_logradouro integer,
	primary key(id)
);

create table cliente (
	id integer not null auto_increment,
	firma varchar(100),
	comprador varchar(50),
	cnpj varchar(15),
	inscricao varchar(15),
	ativo boolean,
	id_logradouro integer,
	primary key(id)
);

create table vendedor (
	id integer not null auto_increment,
	nome varchar(50),
	id_logradouro integer,
	primary key(id)
);


alter table fornecedor
	add constraint fk_fornecedor_logradouro foreign key(id_logradouro)
	references logradouro(id);
alter table cliente
	add constraint fk_cliente_logradouro foreign key(id_logradouro)
	references logradouro(id);
alter table vendedor
	add constraint fk_vendedor foreign key(id_logradouro)
	references logradouro(id);
