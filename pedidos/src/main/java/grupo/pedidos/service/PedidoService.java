package grupo.pedidos.service;

import grupo.pedidos.dto.EventoAltaPedido;
import grupo.pedidos.entities.Pedido;

public interface PedidoService {
	
	Pedido findById(Integer pedidoId);
	
	void altaPedido(Pedido pedido);
	
	void grabarPedido(EventoAltaPedido altaPedido, String estado);
}
