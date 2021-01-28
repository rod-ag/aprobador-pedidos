package grupo.usuarios.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import grupo.usuarios.dto.EventoAltaPedido;
import grupo.usuarios.dto.Respuesta;
import grupo.usuarios.entities.Usuario;
import grupo.usuarios.service.UsuarioService;

@Component
public class AutorizadorSaldoUsuario {

    private static final Logger log = LoggerFactory.getLogger(AutorizadorSaldoUsuario.class);
    private CanalesUsuario canales;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public AutorizadorSaldoUsuario(CanalesUsuario canales) {
        this.canales = canales;
    }

    @StreamListener(CanalesUsuario.ALTA_PEDIDO_IN)
    public void compruebaSaldoUsuario(EventoAltaPedido altaPedido) {
    	
    	Usuario usuario = usuarioService.findById(altaPedido.getUsuarioId());
    	log.info("Inicio autorizador saldo usuario: usuario id {}, importe pago gift {}, saldo previo {}", altaPedido.getUsuarioId(), altaPedido.getImportePagoGift(),
    				usuario.getSaldoGift());
    	
		Respuesta respuesta = new Respuesta();
		respuesta.setEventoAltaPedido(altaPedido);
    	
    	if (altaPedido.getImportePagoGift().doubleValue() > usuario.getSaldoGift().doubleValue()) {
    		respuesta.setRespuesta("RECHAZADO");
    	} else {
    		respuesta.setRespuesta("APROBADO");
    	}
    	
		canales.respuestaUsuario().send(message(respuesta));
		log.info("Respuesta autorizador saldo usuario: {}", respuesta.getRespuesta());
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
