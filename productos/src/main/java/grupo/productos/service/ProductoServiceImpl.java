package grupo.productos.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo.productos.entities.Producto;
import grupo.productos.entities.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	@Autowired
	private ProductoRepository productoRepo;
	
	@Override
	public Producto findById(Integer productoId) {
		return productoRepo.findById(productoId).get();
	}

	@Override
	public void rebajaSaldosProductos(Map<Integer, Integer> productosCantidades) {
    	productosCantidades.forEach((productoId, cantidadPedido) -> {
    		Producto producto = findById(productoId);
    		Integer saldoAnterior = producto.getSaldoStock();
    		rebajaSaldo(producto, cantidadPedido);
    		
    		log.info("Saldo de producto {} - {} rebajado: saldo anterior {}, saldo actual {}", productoId, producto.getDescripcion(), saldoAnterior, producto.getSaldoStock());
    	});
	}

	private void rebajaSaldo(Producto producto, Integer cantidadPedido) {
		producto.setSaldoStock(producto.getSaldoStock().intValue() - cantidadPedido.intValue());
		productoRepo.save(producto);
	}
}
