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
select * from pedido;

delete from pedido;
delete from item_pedido;
--alter table vendedor
--	drop foreign key fk_vendedor;