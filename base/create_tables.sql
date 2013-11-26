create schema if not exists controle;

create table logradouro (
	id integer,
	endereco varchar(120),
	numero integer(6),
	complemento varchar(20),
	bairro varchar(30),
	cep varchar(8),
	cidade varchar(30),
	estado varchar(2),
	email varchar(100),
	ddd varchar(3),
	telefone varchar(9)
);

create table empresa (
	id integer,
	firma varchar(100),
	comprador varchar(50),
	cnpj varchar(15),
	inscricao varchar(15),
	ativo boolean,
	id_logradouro integer,
	id_tipo_empresa integer
);

create table tipo_empresa (
	id integer,
	indicador_tipo varchar(1)
);

create table vendedor (
	id integer,
	nome varchar(50),
	id_logradouro integer
);

alter table logradouro
	add constraint pk_logradouro primary key(id);
alter table empresa
	add constraint pk_empresa primary key(id);
alter table tipo_empresa
	add constraint pk_tipo_empresa primary key(id);
alter table tipo_vendedor
	add constraint pk_vendedor primary key(id);

alter table empresa
	add constraint fk_logradouro foreign key(id)
	references logradouro(id);
alter table vendedor
	add constraint fk_vendedor foreign key(id)
	references logradouro(id);
