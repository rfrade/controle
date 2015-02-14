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
	telefone varchar(12),
	ddd_celular varchar(3),
	celular varchar(12),
	primary key(id)
);

create table fornecedor (
	id integer not null auto_increment,
	firma varchar(100),
	comprador varchar(50),
	cnpj varchar(25),
	inscricao varchar(15),
	ativo boolean,
	id_logradouro integer,
	primary key(id)
);

create table cliente (
	id integer not null auto_increment,
	firma varchar(100),
	comprador varchar(50),
	cnpj varchar(25),
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
	primary key(id),
	id_fornecedor integer,
	id_vendedor integer,
	id_cliente integer,
	transportador varchar(60),
	condicoes varchar(60),
	cobranca varchar(60),
	data_pedido date,
	comissao double,
	entrega varchar(60),
	colecao varchar(30),
	observacao varchar(180),
	desconto1 double,
	desconto2 double,
	desconto3 double,
	desconto4 double,
	desconto_total double,
	valor_total double,
	valor_sub_total double
);

create table produto (
	id integer not null auto_increment,
	referencia varchar(10),
	descricao varchar(100),
	valor_unitario double,
	quantidade integer,
	tamanho varchar(50),
	ativo boolean,
	id_fornecedor integer,
	primary key(id)
);

create table recebimento (
	id integer not null auto_increment,
	id_pedido integer,
	data_recebimento date,
	valor_recebimento double,
	recebido boolean,
	percentual_comissao double,
	primary key(id)
);

create table item_pedido (
	id integer not null auto_increment,
	primary key(id),
	id_pedido integer,
	id_produto integer,
	descricao varchar(100),
	cor varchar(20),
	observacao varchar(100),
	valor_total double,
	quantidade_total integer,
	quantidade_tamanho_1 integer,
	quantidade_tamanho_2 integer,
	quantidade_tamanho_3 integer,
	quantidade_tamanho_4 integer,
	quantidade_tamanho_5 integer,
	quantidade_tamanho_6 integer,
	quantidade_tamanho_7 integer,
	quantidade_tamanho_8 integer
);

create table parametro (
	id integer not null auto_increment,
	primary key(id),
	chave varchar(40),
	valor varchar(400),
	descricao varchar(200)
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
alter table item_pedido
	add constraint fk_item_pedido_produto foreign key(id_produto)
	references produto(id);
alter table recebimento
	add constraint fk_recebimento_pedido foreign key(id_pedido)
	references pedido(id);
alter table produto
	add constraint fk_produto_fornecedor foreign key(id_fornecedor)
	references fornecedor(id);

insert into parametro(chave, 
					valor, 
					descricao)
	values('caminho_diretorio_importacao', 
		   'Alterar',
		   'Caminho do diretório de importação da planilha de produtos');
		   
insert into parametro(chave, 
					valor, 
					descricao)
	values('caminho_relatorio_recebimentos', 
		   'Alterar',
		   'Diretório onde será salvo o relatorio de recebimentos');
		   
insert into parametro(chave, 
					valor, 
					descricao)
	values('caminho_relatorio_pedido', 
		   'Alterar',
		   'Diretório onde será salvo o relatorio do pedido');
		   
insert into parametro(chave, 
					valor, 
					descricao)
	values('caminho_mysql_dump', 
		   'Alterar',
		   'Caminho na instalação do mysql onde se encontra o mysqldump.exe');