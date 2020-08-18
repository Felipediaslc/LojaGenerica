package br.com.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class Configuracao {

	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);

	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
