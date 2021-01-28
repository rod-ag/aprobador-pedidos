package grupo.usuarios.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo.usuarios.dto.EventoEstadoPedido;
import grupo.usuarios.entities.Usuario;
import grupo.usuarios.entities.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	UsuarioRepository usuarioRepo;

	@Override
	public Usuario findById(Integer usuarioId) {
		return usuarioRepo.findById(usuarioId).get();
	}
	
	@Override
	public void rebajaSaldoGift(EventoEstadoPedido eventoEstadoPedido) {
		Usuario usuario = findById(eventoEstadoPedido.getUsuarioId());
		Double saldoAnterior = usuario.getSaldoGift();
		usuario.setSaldoGift(usuario.getSaldoGift().doubleValue() - eventoEstadoPedido.getImportePagoGift().doubleValue());
		usuarioRepo.save(usuario);
		
		log.info("Saldo gift de usuario {} - {} rebajado: saldo anterior {}, saldo actual {}", usuario.getUsuarioId(), usuario.getNombre().concat(" ")
					.concat(usuario.getApellidos()), saldoAnterior, usuario.getSaldoGift());
	}
}
