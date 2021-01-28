package grupo.productos.service;

import java.util.Map;

import grupo.productos.entities.Producto;

public interface ProductoService {
	
	Producto findById(Integer productoId);
	
	void rebajaSaldosProductos(Map<Integer, Integer> productosCantidades);
}
