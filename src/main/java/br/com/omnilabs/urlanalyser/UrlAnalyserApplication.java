package br.com.omnilabs.urlanalyser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableRabbit
@EnableCaching
@Slf4j
public class UrlAnalyserApplication {

	public static void main(String[] args) {

		// This an workaround to avoid application starts up before its dependencies.
		// Pls don't blame me up :)
		try {
			log.info("Awaiting to startup...");
			Thread.sleep(10000L);
			SpringApplication.run(UrlAnalyserApplication.class, args);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
