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

create table pedido (
	id integer not null auto_increment,
	transportador varchar(60),
	condicoes varchar(60),
	cobranca varchar(60),
	data_pedido date,
	comicao double,
	entrega varchar(60),
	colecao varchar(30),
	id_fornecedor integer,
	id_vendedor integer,
	id_cliente integer,
	primary key(id)
);

create table item_pedido (
	id integer not null auto_increment,
	referencia varchar(10),
	cor varchar(20),
	descricao varchar(100),
	observacao varchar(100),
	valor_unitario double,
	id_pedido integer,
	primary key(id)
);

create table produto_item_pedido (
	id integer not null auto_increment,
	id_item_pedido integer,
	id_tamanho integer,
	quantidade integer,
	primary key(id)
);

create table recebimento (
	id integer not null auto_increment,
	id_pedido integer,
	data_recebimento date,
	recebido boolean,
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
alter table pedido
	add constraint fk_pedido_fornecedor foreign key(id_fornecedor)
	references fornecedor(id);
alter table pedido
	add constraint fk_pedido_vendedor foreign key(id_vendedor)
	references vendedor(id);
alter table pedido
	add constraint fk_pedido_cliente foreign key(id_cliente)
	references cliente(id);
alter table item_pedido
	add constraint fk_item_pedido_pedido foreign key(id_pedido)
	references pedido(id);
alter table produto_item_pedido
	add constraint fk_produto_item_pedido_item_pedido foreign key(id_item_pedido)
	references item_pedido(id);
alter table recebimento
	add constraint fk_recebimento_pedido foreign key(id_pedido)
	references pedido(id);