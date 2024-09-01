package br.com.lfm.hollywood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages= "br.com.lfm.hollywood.modelos.repositorios")
@EntityScan(basePackages = "br.com.lfm.hollywood.modelos.entidades")
public class HollywoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(HollywoodApplication.class, args);
	}

}
