package grupo.pedidos.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import grupo.pedidos.dto.Respuesta;
import grupo.pedidos.service.PedidoService;

@Component
public class RespuestaAprobadoresListener {
	
	private static final Logger log = LoggerFactory.getLogger(RespuestaAprobadoresListener.class);
	
	@Autowired
	private PedidoService pedidoService;
	
	private Boolean respuestaProductoRecibida = Boolean.FALSE;
	private Boolean respuestaUsuarioRecibida = Boolean.FALSE;
	
	String mensajeRespuestaProducto = null;
	String mensajeRespuestaUsuario = null;
	
	// Suscribirse al canal de "respuestaProducto"
	@StreamListener(CanalesPedido.RESPUESTA_PRODUCTO_IN)
	public void recibeRespuestaProducto(Respuesta mensajeRespuesta) {
		
		respuestaProductoRecibida = Boolean.TRUE;
		mensajeRespuestaProducto = mensajeRespuesta.getRespuesta();
		
		if (respuestaUsuarioRecibida) {
			actualizaEstado(mensajeRespuesta);
		}
	}

	// Suscribirse al canal de "respuestaUsuario"
	@StreamListener(CanalesPedido.RESPUESTA_USUARIO_IN)
	public void recibeRespuestaUsuario(Respuesta mensajeRespuesta) {
		
		respuestaUsuarioRecibida = Boolean.TRUE;
		mensajeRespuestaUsuario = mensajeRespuesta.getRespuesta();
		
		if (respuestaProductoRecibida) {
			actualizaEstado(mensajeRespuesta);
		}
	}

	// Actualizar estado de pedido y publicar evento en canal "estadoPedido"
	private void actualizaEstado(Respuesta mensajeRespuesta) {
		
		final String ESTADO_APROBADO = "APROBADO";
		final String ESTADO_RECHAZADO = "RECHAZADO";
		String estado = null;
		
		// No se actualiza estado del pedido a menos que haya una respuesta de ambos servicios
		if (respuestaUsuarioRecibida && respuestaProductoRecibida) {
			if (ESTADO_APROBADO.contentEquals(mensajeRespuestaUsuario) && ESTADO_APROBADO.equals(mensajeRespuestaProducto)) {
				estado = ESTADO_APROBADO;
			} else {
				estado = ESTADO_RECHAZADO;
			}
			
			pedidoService.grabarPedido(mensajeRespuesta.getEventoAltaPedido(), estado);
			log.info("Estado de pedido id {} actualizado a {}", mensajeRespuesta.getEventoAltaPedido().getPedidoId(), estado);
			
			limpiarAtributos();
		}
	}
	
	private void limpiarAtributos() {
		respuestaProductoRecibida = Boolean.FALSE;
		respuestaUsuarioRecibida = Boolean.FALSE;
		
		mensajeRespuestaProducto = null;
		mensajeRespuestaUsuario = null;
	}
}
