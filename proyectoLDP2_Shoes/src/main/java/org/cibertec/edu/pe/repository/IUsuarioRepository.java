package org.cibertec.edu.pe.repository;

import org.cibertec.edu.pe.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

	public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
		
	    Usuario findByUsuarioAndClave(String usuario, String clave);
	}



