package br.com.omnilabs.urlanalyser;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableRabbit
public class UrlAnalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlAnalyserApplication.class, args);
	}

}
