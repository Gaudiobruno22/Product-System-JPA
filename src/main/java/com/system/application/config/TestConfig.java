package com.system.application.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.system.application.entities.User;
import com.system.application.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Marcos Lopes", "marcos@gmail.com", "98885687" , "123456");
		User u2 = new User(null, "Maria Bonita", "mbonita@gmail.com", "977777777", "654321"); 
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}
