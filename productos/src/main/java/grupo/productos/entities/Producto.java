package grupo.productos.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto implements Serializable {

	private static final long serialVersionUID = -237162779255642772L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productoId;
	
	private String descripcion;
	
	private Integer saldoStock;

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getSaldoStock() {
		return saldoStock;
	}

	public void setSaldoStock(Integer saldoStock) {
		this.saldoStock = saldoStock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((productoId == null) ? 0 : productoId.hashCode());
		result = prime * result + ((saldoStock == null) ? 0 : saldoStock.hashCode());
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
		Producto other = (Producto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (productoId == null) {
			if (other.productoId != null)
				return false;
		} else if (!productoId.equals(other.productoId))
			return false;
		if (saldoStock == null) {
			if (other.saldoStock != null)
				return false;
		} else if (!saldoStock.equals(other.saldoStock))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [productoId=" + productoId + ", descripcion=" + descripcion + ", saldoStock=" + saldoStock
				+ "]";
	}
}
