package grupo.pedidos.restcontroller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import grupo.pedidos.entities.Pedido;
import grupo.pedidos.service.PedidoService;

@RestController
public class PedidoRestController {

	@Autowired
	private PedidoService pedidoService;
	
	@HystrixCommand(fallbackMethod = "fallbackNuevoPedido")
    @PostMapping("/creaPedido")
    public String nuevoPedido(@RequestBody Pedido pedido) {
		
		if (pedido.getUsuarioId() == null) {
			throw new RuntimeException("El pedido debe incluir un usuario.\n");
		}
		
    	pedidoService.altaPedido(pedido);
    	
    	return "Se ha dado de alta el pedido número " + pedido.getPedidoId() + "\n";
    }
	
	public String fallbackNuevoPedido(Pedido pedido, Throwable t) {
		
		final String MENSAJE_ESTANDAR = "El servicio de alta de pedido no puede responder en este momento. Por favor pruebe más tarde ...\n";
		
		if (t == null) {
			return MENSAJE_ESTANDAR;
		}
		
		if (StringUtils.isBlank(t.getMessage())) {
			return MENSAJE_ESTANDAR;
		}
		
		return t.getMessage();
	}
}
