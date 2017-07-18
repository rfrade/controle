drop table item_pedido;
drop table recebimento;
drop table produto;
drop table pedido;
drop table fornecedor;
drop table cliente;
drop table vendedor;
drop table logradouro;



select * from logradouro;
select * from cliente;
select * from produto;
select * from item_pedido;
select id, id_recebimento from pedido;
select id, id_pedido from recebimento;

select * from pedido where id > 505 and id < 508;

select * from item_pedido where id_pedido = 739;
select * from item_pedido where id = 11226;
11369

select count(*) from recebimento;
select max(id) from recebimento where id = 524;
select * from recebimento where id_pedido = 736;

select * from pedido;

delete from recebimento where id in (9, 210, 524, 525);
delete from item_pedido where id in (26, 27);

--delete from produto;
--delete from pedido;
--delete from item_pedido;
--alter table pedido add valor_comissionado double;
--alter table produto add id_fornecedor integer;

update Produto p set p.ativo = false where p.referencia = 'asdf'  and p.ativo = true and p.id_fornecedor = 800;

source C:/desenvolvimento/projetos/controle/base/backup.sql;

-- Alteração do valor faturado 06/04/2015

alter table recebimento add column valor_faturado double;

update recebimento as r inner join pedido as p on r.id_pedido = p.id
	set r.valor_faturado = p.valor_total;

select pedido.id, pedido.valor_sub_total, recebimento.valor_faturado from recebimento inner join pedido on recebimento.id_pedido = pedido.id;