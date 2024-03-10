package org.cibertec.edu.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.cibertec.edu.pe.model.Ventas;

public interface IVentasRepository extends JpaRepository<Ventas, Integer> {
	Ventas findByIdVentas(int id);

}
