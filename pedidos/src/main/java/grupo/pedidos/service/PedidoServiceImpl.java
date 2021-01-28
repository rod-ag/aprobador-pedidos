package grupo.pedidos.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import grupo.pedidos.components.CanalesPedido;
import grupo.pedidos.dto.EventoAltaPedido;
import grupo.pedidos.dto.EventoEstadoPedido;
import grupo.pedidos.entities.LineaPedido;
import grupo.pedidos.entities.LineaPedidoRepository;
import grupo.pedidos.entities.Pedido;
import grupo.pedidos.entities.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {
	
    private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);
    private CanalesPedido canales;
    
    @Autowired
    public PedidoServiceImpl(CanalesPedido canales) {
        this.canales = canales;
    }
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private LineaPedidoRepository lineaPedidoRepo;
	
	@Override
	public Pedido findById(Integer pedidoId) {
		return pedidoRepo.findById(pedidoId).get();
	}
	
	// Actualizamos estado del pedido y generamos evento estado pedido para enviar al canal "EstadoPedido". Permite a los otros servicios saber si deben rebajar saldos
	@Override
	public void grabarPedido(EventoAltaPedido altaPedido, String estado) {
		Pedido pedido = findById(altaPedido.getPedidoId());
		pedido.setEstado(estado);
		pedidoRepo.save(pedido);
		
		EventoEstadoPedido eventoEstadoPedido = new EventoEstadoPedido();
		eventoEstadoPedido.setPedidoId(altaPedido.getPedidoId());
		eventoEstadoPedido.setUsuarioId(altaPedido.getUsuarioId());
		eventoEstadoPedido.setProductosCantidades(altaPedido.getProductosCantidades());
		eventoEstadoPedido.setImportePagoGift(altaPedido.getImportePagoGift());
		eventoEstadoPedido.setEstadoPedido(estado);
		
    	canales.estadoPedido().send(message(eventoEstadoPedido));
    	log.info("Evento estado de pedido publicado, pedidoId: {}", altaPedido.getPedidoId());
	}

	// Damos de alta el pedido con estado PENDIENTE y se genera evento alta pedido para enviar al canal "AltaPedido", para su aprobación o rechazo
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void altaPedido(Pedido pedido) {
		log.info("Inicio alta pedido con id {}", pedido.getPedidoId());
		
		pedidoRepo.save(pedido);
		pedido.getLineasPedido().forEach(linea -> {
			linea.setPedido(pedido);    // Necesario para alimentar el FK PEDIDO_ID en tabla de lineas
			lineaPedidoRepo.save(linea);
		});
		
		EventoAltaPedido altaPedido = new EventoAltaPedido();
		altaPedido.setPedidoId(pedido.getPedidoId());
		altaPedido.setUsuarioId(pedido.getUsuarioId());
		altaPedido.setProductosCantidades(getCantidades(pedido));
		altaPedido.setImportePagoGift(pedido.getImportePagoGift());
		
		canales.altaPedido().send(message(altaPedido));
		
		log.info("Evento alta pedido publicado, EventoAltaPedido.pedidoId: {}", altaPedido.getPedidoId());
	}
	
    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
    
    private Map<Integer, Integer> getCantidades(Pedido pedido) {
    	return pedido.getLineasPedido().stream().collect(Collectors.toMap(LineaPedido::getProductoId, LineaPedido::getCantidad));
    }
}
