package grupo.usuarios.service;

import grupo.usuarios.dto.EventoEstadoPedido;
import grupo.usuarios.entities.Usuario;

public interface UsuarioService {

	Usuario findById(Integer usuarioId);
	
	void rebajaSaldoGift(EventoEstadoPedido eventoEstadoPedido);
}
