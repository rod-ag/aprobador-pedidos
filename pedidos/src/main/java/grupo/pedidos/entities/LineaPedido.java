package grupo.pedidos.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineaPedido implements Serializable {
	
	private static final long serialVersionUID = 8757198201200120590L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer lineaPedidoId;
	
	private Integer productoId;
	
	private Double precio;
	
	private Integer cantidad;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PEDIDO_ID")
    private Pedido pedido;
	
	public Integer getLineaPedidoId() {
		return lineaPedidoId;
	}

	public void setLineaPedidoId(Integer lineaPedidoId) {
		this.lineaPedidoId = lineaPedidoId;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LineaPedido )) return false;
        return lineaPedidoId != null && lineaPedidoId.equals(((LineaPedido) obj).getLineaPedidoId());
	}

	@Override
	public String toString() {
		return "LineaPedido [lineaPedidoId=" + lineaPedidoId + ", productoId=" + productoId + ", precio=" + precio
				+ ", cantidad=" + cantidad + ", pedido=" + pedido + "]";
	}
}
