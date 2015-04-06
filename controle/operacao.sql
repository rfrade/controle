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

--delete from produto;
--delete from pedido;
--delete from item_pedido;
--alter table pedido add valor_comissionado double;
--alter table produto add id_fornecedor integer;

update Produto p set p.ativo = false where p.referencia = 'asdf'  and p.ativo = true and p.id_fornecedor = 800;


-- Alteração do valor faturado 06/04/2015

alter table recebimento add column valor_faturado double;

update recebimento as r inner join pedido as p on r.id_pedido = p.id
	set r.valor_faturado = p.valor_total;

select pedido.id, pedido.valor_sub_total, recebimento.valor_faturado from recebimento inner join pedido on recebimento.id_pedido = pedido.id;