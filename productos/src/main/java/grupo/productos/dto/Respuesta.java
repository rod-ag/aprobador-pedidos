package grupo.productos.dto;

import java.io.Serializable;

public class Respuesta implements Serializable {

	private static final long serialVersionUID = -4611129721425167299L;

	private EventoAltaPedido eventoAltaPedido;
	
	private String respuesta;

	public EventoAltaPedido getEventoAltaPedido() {
		return eventoAltaPedido;
	}

	public void setEventoAltaPedido(EventoAltaPedido eventoAltaPedido) {
		this.eventoAltaPedido = eventoAltaPedido;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "Respuesta [eventoAltaPedido=" + eventoAltaPedido + ", respuesta=" + respuesta + "]";
	}
}