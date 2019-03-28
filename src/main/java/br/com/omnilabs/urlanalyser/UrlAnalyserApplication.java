package br.com.omnilabs.urlanalyser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@Slf4j
public class UrlAnalyserApplication {

	public static void main(String[] args) {

		try {
			log.info("Awaiting to startup...");
			Thread.sleep(5000);
			SpringApplication.run(UrlAnalyserApplication.class, args);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
