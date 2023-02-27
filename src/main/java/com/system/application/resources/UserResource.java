package com.system.application.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.application.entities.User;

@RestController
//Caminho do meu Recurso - Request Mapping
@RequestMapping(value = "/users")
public class UserResource {

	//Método que Responde a Requisição Web
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria", "maria@gmail.com", "1199123456", "123465");
		return ResponseEntity.ok().body(u);
	}
}
