package grupo.usuarios.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2039959503794708267L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer usuarioId;

	private String nombre;
    
	private String apellidos;
    
	private Double saldoGift;
    
    public Usuario() {
	}

	public Integer getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Integer id) {
        this.usuarioId = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public Double getSaldoGift() {
        return saldoGift;
    }
    
    public void setSaldoGift(Double saldoGift) {
        this.saldoGift = saldoGift;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((saldoGift == null) ? 0 : saldoGift.hashCode());
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
        Usuario other = (Usuario) obj;
        if (apellidos == null) {
            if (other.apellidos != null)
                return false;
        } else if (!apellidos.equals(other.apellidos))
            return false;
        if (usuarioId == null) {
            if (other.usuarioId != null)
                return false;
        } else if (!usuarioId.equals(other.usuarioId))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (saldoGift == null) {
            if (other.saldoGift != null)
                return false;
        } else if (!saldoGift.equals(other.saldoGift))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [usuarioId=" + usuarioId + ", nombre=" + nombre + ", apellidos=" + apellidos + ", saldoGift=" + saldoGift
                + "]";
    }
}
