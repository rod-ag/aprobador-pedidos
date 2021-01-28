package grupo.pedidos.components;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface CanalesPedido {

	String ALTA_PEDIDO_OUT = "altaPedido";
	String ESTADO_PEDIDO_OUT = "estadoPedido";
	
    @Output(ALTA_PEDIDO_OUT)
    MessageChannel altaPedido();

    @Output(ESTADO_PEDIDO_OUT)
    MessageChannel estadoPedido();

	String RESPUESTA_USUARIO_IN = "respuestaUsuario";
    String RESPUESTA_PRODUCTO_IN = "respuestaProducto";
    
    @Input(RESPUESTA_USUARIO_IN)
    SubscribableChannel respuestaUsuario();

    @Input(RESPUESTA_PRODUCTO_IN)
    SubscribableChannel respuestaProducto();
}
