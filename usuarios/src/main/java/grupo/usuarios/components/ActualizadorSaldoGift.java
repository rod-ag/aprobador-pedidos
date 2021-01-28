package grupo.usuarios.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import grupo.usuarios.dto.EventoEstadoPedido;
import grupo.usuarios.service.UsuarioService;

@Component
public class ActualizadorSaldoGift {
    private static final Logger log = LoggerFactory.getLogger(ActualizadorSaldoGift.class);
    private static final String ESTADO_APROBADO = "APROBADO";
    
    @Autowired
    private UsuarioService usuarioService;
    
    @StreamListener(CanalesUsuario.ESTADO_PEDIDO_IN)
    public void compruebaEstadoPedido(EventoEstadoPedido eventoEstadoPedido) {

    	if (ESTADO_APROBADO.equals(eventoEstadoPedido.getEstadoPedido())) {
    		usuarioService.rebajaSaldoGift(eventoEstadoPedido);
    		log.info("Saldos gift rebajados con pedido {}", eventoEstadoPedido.getPedidoId());
    	}
    }
}
