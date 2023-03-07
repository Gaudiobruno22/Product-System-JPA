package com.system.application.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.system.application.entities.Category;
import com.system.application.repositories.CategoryRepository;
import com.system.application.services.exception.DataBaseException;
import com.system.application.services.exception.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		try {
			Optional<Category> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		}
		catch(RuntimeException e) {
			throw new RuntimeException();
		}
	}
	
	public Category insert(Category cat) {
		try {
			return repository.save(cat);
		}
		catch(RuntimeException e) {
			 e.printStackTrace();
		}
		finally {
			return repository.save(cat);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
		catch(RuntimeException e) {
			e.getMessage();
		}
	}
	
	public Category update(Long id, Category obj) {
		try {
			Category entity = repository.getReferenceById(id);
			updateCategory(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(RuntimeException e) {
			throw new RuntimeException();
		}
	}

	private void updateCategory(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
}
