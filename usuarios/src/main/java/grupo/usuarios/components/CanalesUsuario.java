package grupo.usuarios.components;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface CanalesUsuario {
	
    String ALTA_PEDIDO_IN = "altaPedido";
	String ESTADO_PEDIDO_IN = "estadoPedido";
    
    @Input(ALTA_PEDIDO_IN)
    SubscribableChannel altaPedido();

    @Input(ESTADO_PEDIDO_IN)
    SubscribableChannel estadoPedido();
    
    String RESPUESTA_USUARIO_OUT = "respuestaUsuario";
    
    @Output(RESPUESTA_USUARIO_OUT)
    MessageChannel respuestaUsuario();
}