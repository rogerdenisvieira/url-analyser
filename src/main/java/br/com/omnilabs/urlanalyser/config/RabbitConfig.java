package br.com.omnilabs.urlanalyser.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    @Value("${insertion.queue}")
    private String insertionQueue;

    @Value("${validation.queue}")
    private String validationQueue;

    @Value("${response.exchange}")
    private String responseExchange;

    @Value("${response.routing.key}")
    private String responseRoutingKey;

    @Bean(name = "insertionQueue")
    public Queue insertionQueue(){
        return new Queue(this.insertionQueue, true);
    }


    @Bean
    public Queue createValidationQueue(){
        return new Queue(this.validationQueue, true);
    }

    @Bean
    public Queue createInsertionQueue() {
        return new Queue(this.insertionQueue, true);
    }

    @Bean
    public TopicExchange createResponseExchange(){
        return new TopicExchange(this.responseExchange);
    }

    @Bean
    public Binding createBinding(TopicExchange topicExchange){
        return BindingBuilder.bind(topicExchange).to(topicExchange).with(this.responseRoutingKey);
    }


    public String getResponseExchange() {
        return responseExchange;
    }

    public String getResponseRoutingKey() {
        return responseRoutingKey;
    }
}
