 package com.system.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.system.application.entities.User;
import com.system.application.repositories.UserRepository;
import com.system.application.services.exception.DataBaseException;
import com.system.application.services.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long Id) {
		try {
			repository.deleteById(Id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(Id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public User update(Long Id, User obj) {
		User entity = repository.getReferenceById(Id);
		updateUser(entity, obj);
		return repository.save(entity);
	}

	private void updateUser(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
