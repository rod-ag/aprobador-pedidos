package grupo.productos.components;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import grupo.productos.dto.EventoAltaPedido;
import grupo.productos.dto.Respuesta;
import grupo.productos.entities.Producto;
import grupo.productos.service.ProductoService;

@Component
public class AutorizadorSaldoStock {

    private static final Logger log = LoggerFactory.getLogger(AutorizadorSaldoStock.class);
    private CanalesProducto canales;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    public AutorizadorSaldoStock(CanalesProducto canales) {
    	this.canales = canales;
    }
    
    @StreamListener(CanalesProducto.ALTA_PEDIDO_IN)
    public void compruebaSaldoStock(EventoAltaPedido altaPedido) {
    	Boolean existeRechazo = Boolean.FALSE;
    	
    	for (Map.Entry<Integer, Integer> entry : altaPedido.getProductosCantidades().entrySet()) {
    		Producto producto = productoService.findById(entry.getKey());
        	log.info("Inicio autorizador saldo stock: producto id {}, cantidad pedido {}, saldo previo {}", entry.getKey(), entry.getValue(), producto.getSaldoStock());

        	if (entry.getValue().intValue() > producto.getSaldoStock().intValue()) {
        		existeRechazo = Boolean.TRUE;
        	}
        }
    	
		Respuesta respuesta = new Respuesta();
		respuesta.setEventoAltaPedido(altaPedido);
    	
    	if (existeRechazo) {
    		respuesta.setRespuesta("RECHAZADO");
    	} else {
        	respuesta.setRespuesta("APROBADO");
    	}
    	
		canales.respuestaProducto().send(message(respuesta));
		log.info("Respuesta autorizador saldo stock: {}", respuesta.getRespuesta());
    }
    
    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
