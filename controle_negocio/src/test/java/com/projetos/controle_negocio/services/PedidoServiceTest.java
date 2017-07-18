package com.projetos.controle_negocio.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projetos.controle_entities.Pedido;
import com.projetos.controle_negocio.filtro.Comparador;
import com.projetos.controle_negocio.filtro.Filtro;
import com.projetos.controle_negocio.filtro.TipoFiltro;
import com.projetos.controle_negocio.service.base.PedidoService;

public class PedidoServiceTest extends BaseServiceTest {

	@Autowired
	public PedidoService pedidoService;

	@Test
	public void listarPedidosRecentes() {
		try {
			Calendar calendar = Calendar.getInstance();
			// altera o mês para 3 meses antes
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 60);
			Date data = calendar.getTime();
			Filtro filtro = new Filtro("dataPedido", TipoFiltro.DATE, Comparador.GREATER_OR_EQUALS, data);
			
			long ini = Calendar.getInstance().getTimeInMillis();
			List<Pedido> pedidos = pedidoService.filtrar(filtro);
			//List<Pedido> pedidos = pedidoService.listar();
			long fim = Calendar.getInstance().getTimeInMillis();
			log.info("número de pedidos: " + pedidos.size());
			log.info("tempo: " + (fim - ini));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	//@Test
	public void consultarPedidoTest() {
		try {
			Pedido pedido = pedidoService.consultarPedido(1);
			log.info(pedido.getItensPedido().size());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
}
