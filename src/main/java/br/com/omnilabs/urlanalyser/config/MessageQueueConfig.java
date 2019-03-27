package br.com.omnilabs.urlanalyser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {


    @Value("${queue.insertion}")
    private String insertionQueue;

    @Value("${queue.validation}")
    private String validationQueue;


    @Bean(name = "insertionQueue")
    public Queue insertionQueue(){
        return new Queue(this.insertionQueue, true);
    }

    @Bean(name = "validationQueue")
    public Queue validationQueue(){
        return new Queue(this.validationQueue, true);
    }

    public String getInsertionQueue() {
        return insertionQueue;
    }

    public String getValidationQueue() {
        return validationQueue;
    }
}
