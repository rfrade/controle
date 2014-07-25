drop table empresa;
drop table fornecedor;
drop table cliente;
drop table vendedor;
drop table logradouro;
drop table item_pedido;
drop table produto;
drop table recebimento;
drop table pedido;

select * from logradouro;
select * from cliente;
select * from produto;
select * from item_pedido;
select id, id_recebimento from pedido;
select id, id_pedido from recebimento;

delete from recebimento where id = 28;
delete from item_pedido where id in (26, 27);

--delete from pedido;
--delete from item_pedido;
--alter table pedido add valor_comissionado double;
--alter table produto add id_fornecedor integer;

select * from recebimento;
--alter table vendedor
--	drop foreign key fk_vendedor;