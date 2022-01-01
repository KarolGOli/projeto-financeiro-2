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

import com.example.demo.modelo.Usuario;
import com.example.demo.repositorio.UsuarioRepositorio;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepositorio userRepositorio;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> addUsuario (@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder){
		Usuario userSalvo = userRepositorio.save(usuario);
		
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(userSalvo.getLogin()).toUri();
		
		return ResponseEntity.created(uri).build(); 
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listaUsuarios(){
		return ResponseEntity.ok().body(userRepositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioId(@PathVariable Long id){
		return ResponseEntity.ok().body(userRepositorio.findById(id).get());
	}
	
}
