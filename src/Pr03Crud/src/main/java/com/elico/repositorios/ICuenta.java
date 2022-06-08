package com.elico.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elico.modelos.Cuenta;

public interface ICuenta extends JpaRepository<Cuenta, Integer>{

	
}
