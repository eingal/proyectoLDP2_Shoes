package org.cibertec.edu.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.cibertec.edu.pe.model.Detalle;

public interface IDetalleRepository extends JpaRepository<Detalle, Integer> {
	Detalle findByIdDetalle(int id);

}
