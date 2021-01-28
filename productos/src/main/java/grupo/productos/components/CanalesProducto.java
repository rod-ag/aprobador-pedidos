package grupo.productos.components;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface CanalesProducto {
	
    String ALTA_PEDIDO_IN = "altaPedido";
	String ESTADO_PEDIDO_IN = "estadoPedido";
    
    @Input(ALTA_PEDIDO_IN)
    SubscribableChannel altaPedido();
    
    @Input(ESTADO_PEDIDO_IN)
    SubscribableChannel estadoPedido();
    
    String RESPUESTA_PRODUCTO_OUT = "respuestaProducto";
    
    @Output(RESPUESTA_PRODUCTO_OUT)
    MessageChannel respuestaProducto();
}