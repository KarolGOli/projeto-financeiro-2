package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.modelo.Financas;
import com.example.demo.repositorio.FinancasRepositorio;

@RestController
@RequestMapping("/financas")
public class FinancasController {
	
	@Autowired
	FinancasRepositorio financasRepositorio;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> addUsuario (@RequestBody @Valid Financas financas, UriComponentsBuilder uriBuilder){
		Financas financaSalvo = financasRepositorio.save(financas);
		
		URI uri = uriBuilder.path("/financas/{id}").buildAndExpand(financaSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).build(); 
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Financas>> listaUsuarios(){
		return ResponseEntity.ok().body(financasRepositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Financas> buscaUsuarioId(@PathVariable Long id){
		return ResponseEntity.ok().body(financasRepositorio.findById(id).get());
	}

}
