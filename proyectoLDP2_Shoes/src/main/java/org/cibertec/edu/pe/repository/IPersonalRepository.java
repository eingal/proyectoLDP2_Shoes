package org.cibertec.edu.pe.repository;

import org.cibertec.edu.pe.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonalRepository extends JpaRepository<Personal, Integer> {
    Personal findByIdPersonal(int id);
    
    Personal findFirstByDni(String dni);
    
    long count();
}

