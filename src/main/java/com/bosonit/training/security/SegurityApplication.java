package com.bosonit.training.security;

import com.bosonit.training.security.persona.entity.Persona;
import com.bosonit.training.security.persona.infrastructure.repository.PersonaDao;
import com.bosonit.training.security.persona.service.ServicePersona;
import com.bosonit.training.security.role.entity.Role;
import com.bosonit.training.security.role.infrastructure.repository.RepositoryRole;
import com.bosonit.training.security.role.service.ServiceRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
@Slf4j
@SpringBootApplication
public class SegurityApplication implements CommandLineRunner {

	@Autowired
	ServicePersona servicePersona;

	@Autowired
	ServiceRole serviceRole;

	@Autowired
	RepositoryRole repositoryRole;
	@Autowired
	PersonaDao personaDao;


	public static void main(String[] args) {
		SpringApplication.run(SegurityApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Override
	public void run(String... args) throws Exception {



		Role role = new Role();
		role.setNameRole("USER");
		repositoryRole.save(role);
		Persona persona=new Persona(null,"sadio", "sadio12", "$2a$10$kPhxcBrXlJVfFQo3j6EM5OXOizvlvkFmV62BAJX2.0Mvh4tbBwO/6",new ArrayList<>());
		persona.getRoles().add(role);
		personaDao.save(persona);


		Role role1=new Role();
		role1.setNameRole("ADMIN");
		repositoryRole.save(role1);
		Persona persona1=new Persona(null, "cristiano", "cristo7", "$2a$10$OUivQ6xG4X3YNv1li28JsOy5zutnv6O1.6X67vGcuXOWV2KVt0sn2",new ArrayList<>());
		persona.getRoles().add(role1);
		personaDao.save(persona1);

//			log.info(String.valueOf(servicePersona.loadpersona(new PersonaINputDto(null,"sadio", "$2a$10$/EFGNAc7YcT9Fjbc3rRtNuWwWex/G98f0IHJABy6LMMhEcnvufLQe", "sadio Mane", "sadio.mane@bayer.com",new ArrayList<>()))));

	}
}


