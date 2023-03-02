package com.system.application.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.system.application.entities.Category;
import com.system.application.entities.Order;
import com.system.application.entities.OrderItem;
import com.system.application.entities.Payment;
import com.system.application.entities.Product;
import com.system.application.entities.User;
import com.system.application.entities.enums.OrderStatus;
import com.system.application.repositories.CategoryRepository;
import com.system.application.repositories.OrderItemRepository;
import com.system.application.repositories.OrderRepository;
import com.system.application.repositories.ProductRepository;
import com.system.application.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Marcos Lopes", "marcos@gmail.com", "98885687" , "123456");
		User u2 = new User(null, "Maria Bonita", "mbonita@gmail.com", "977777777", "654321"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Um Livro muito bom e Interessate.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Ela é Esperta. Mas é uma TV.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Notebook de Riquinho muito bom e Barato", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Para a Galerinha dos Compiuter", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Não sei o que é isso, mas está a venda", 100.99, ""); 

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		//Association of Objects Start's Here:
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		//Saving with the Association of Categories: Table ManyToMany on Database.
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		//For Dependent Classes, We Don's Call your respective Service, you make a Save from the Independent Class.
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		
	}
}
