package com.example.notesapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.notesapp.model.User;
import com.example.notesapp.repository.UserREPO;

@SpringBootApplication
public class NotesappApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesappApplication.class, args);
	}
	@Bean
	CommandLineRunner addTestUser(UserREPO repo, PasswordEncoder encoder){
		return args -> {
			if(repo.findByUsername("admin").isEmpty()){
				User user = new User();
				user.setUsername("admin");
				user.setPassword(encoder.encode("password"));
				repo.save(user);
			}
		};
	}

}
