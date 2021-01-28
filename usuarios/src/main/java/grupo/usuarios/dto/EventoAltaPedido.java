package grupo.usuarios.dto;

import java.io.Serializable;
import java.util.Map;

public class EventoAltaPedido implements Serializable {

	private static final long serialVersionUID = -5972452978503628357L;
	
	private Integer pedidoId;

	private Integer usuarioId;
    
	private Map<Integer, Integer> productosCantidades;
    
	private Double importePagoGift;

    public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Map<Integer, Integer> getProductosCantidades() {
        return productosCantidades;
    }

    public void setProductosCantidades(Map<Integer, Integer> productosCantidades) {
        this.productosCantidades = productosCantidades;
    }

    public Double getImportePagoGift() {
        return importePagoGift;
    }

    public void setImportePagoGift(Double totalPedido) {
        this.importePagoGift = totalPedido;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((importePagoGift == null) ? 0 : importePagoGift.hashCode());
		result = prime * result + ((pedidoId == null) ? 0 : pedidoId.hashCode());
		result = prime * result + ((productosCantidades == null) ? 0 : productosCantidades.hashCode());
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoAltaPedido other = (EventoAltaPedido) obj;
		if (importePagoGift == null) {
			if (other.importePagoGift != null)
				return false;
		} else if (!importePagoGift.equals(other.importePagoGift))
			return false;
		if (pedidoId == null) {
			if (other.pedidoId != null)
				return false;
		} else if (!pedidoId.equals(other.pedidoId))
			return false;
		if (productosCantidades == null) {
			if (other.productosCantidades != null)
				return false;
		} else if (!productosCantidades.equals(other.productosCantidades))
			return false;
		if (usuarioId == null) {
			if (other.usuarioId != null)
				return false;
		} else if (!usuarioId.equals(other.usuarioId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventoAltaPedido [pedidoId=" + pedidoId + ", usuarioId=" + usuarioId + ", productosCantidades="
				+ productosCantidades + ", importePagoGift=" + importePagoGift + "]";
	}
}
