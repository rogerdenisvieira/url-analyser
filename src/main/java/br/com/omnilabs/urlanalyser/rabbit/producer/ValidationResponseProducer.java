package br.com.omnilabs.urlanalyser.rabbit.producer;

import br.com.omnilabs.urlanalyser.config.RabbitConfig;
import br.com.omnilabs.urlanalyser.rabbit.message.ValidationResponseMessage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidationResponseProducer {


    private RabbitTemplate rabbitTemplate;
    private RabbitConfig rabbitConfig;
    private Gson gson;

    @Autowired
    public ValidationResponseProducer(RabbitTemplate rabbitTemplate, RabbitConfig rabbitConfig, Gson gson) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfig = rabbitConfig;
        this.gson = gson;
    }

    public void send(ValidationResponseMessage validationResponseMessage){
        log.info("Sending response to exchange [{}] with routing key [{}]", rabbitConfig.getResponseExchange(), rabbitConfig.getResponseRoutingKey());
        rabbitTemplate.convertAndSend(rabbitConfig.getResponseExchange(), rabbitConfig.getResponseRoutingKey(), gson.toJson(validationResponseMessage));
    }
}
