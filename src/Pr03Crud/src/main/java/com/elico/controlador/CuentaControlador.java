package com.elico.controlador;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elico.modelos.Cuenta;
import com.elico.servicios.CuentaServicio;

@RestController
public class CuentaControlador {

	@Autowired
	private CuentaServicio cuentaServicio;
	
	@GetMapping(path="/cuentas")
	private ResponseEntity<List<Cuenta>> obtenerCuentas(){
		return ResponseEntity.ok(cuentaServicio.findAll());
	}
	
	@GetMapping(path="/cuentas/{id}")
	private ResponseEntity<Optional<Cuenta>> obtenerCuentasId(@PathVariable("id") int id){
		return ResponseEntity.ok(cuentaServicio.findById(id));
	}
	
	@PostMapping(path="/cuentas")
	private ResponseEntity<Map<String, String>> crearCuenta(@ModelAttribute Cuenta cuenta){
		
		try {
			cuentaServicio.save(cuenta);
			Map<String, String> respuesta = new HashMap<String, String>();
			respuesta.put("respuesta", "si");
			return ResponseEntity.created(new URI("/cuentas/" + cuenta.getId())).body(respuesta);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@PutMapping(path="/cuentas/{id}")
	private ResponseEntity<Map<String, String>> actualizarCuenta(@ModelAttribute Cuenta cuenta, @PathVariable("id") int id){
		
		try {
			Cuenta cuentaActualizar = cuentaServicio.findById(id).get();
			cuentaActualizar.setId(cuenta.getId());
			cuentaActualizar.setCuenta(cuenta.getCuenta());
			System.out.println("DATOS: "+cuenta.getCuenta()+", "+cuenta.getCuenta()+", "+id);
			cuentaServicio.save(cuentaActualizar);
			Map<String, String> respuesta = new HashMap<String, String>();
			respuesta.put("respuesta", "si");
			return ResponseEntity.ok(respuesta);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@PatchMapping(path="/cuentas/{id}")
	private ResponseEntity<Map<String, String>> actualizarParcialCuenta(@ModelAttribute Cuenta cuenta, @PathVariable("id") int id){
		
		try {
			Cuenta cuentaActualizar = cuentaServicio.findById(id).get();
			cuentaActualizar.setCuenta(cuenta.getCuenta());
			cuentaServicio.save(cuentaActualizar);
			Map<String, String> respuesta = new HashMap<String, String>();
			respuesta.put("respuesta", "si");
			return ResponseEntity.ok(respuesta);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping("/cuentas/{id}")
	private ResponseEntity<Map<String, String>> eliminarCuenta(@PathVariable("id") int id){
		try {
			cuentaServicio.deleteById(id);
			Map<String, String> respuesta = new HashMap<String, String>();
			if(!cuentaServicio.existsById(id)) {
				respuesta.put("respuesta", "si");
			}else {
				respuesta.put("respuesta", "no");
			}
			return ResponseEntity.ok(respuesta);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
