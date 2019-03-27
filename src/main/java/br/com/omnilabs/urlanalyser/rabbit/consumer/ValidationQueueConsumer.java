package br.com.omnilabs.urlanalyser.rabbit.consumer;

import br.com.omnilabs.urlanalyser.config.MessageQueueConfig;
import br.com.omnilabs.urlanalyser.rabbit.message.RegexWhitelistMessage;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class ValidationQueueConsumer {


    private MessageQueueConfig config;
    private RabbitTemplate rabbitTemplate;
    private Queue queue;
    private Gson gson;

    @Autowired
    public ValidationQueueConsumer(MessageQueueConfig config,
                                   RabbitTemplate rabbitTemplate,
                                   @Qualifier("validationQueue") Queue queue,
                                   Gson gson) {
        this.config = config;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.gson = gson;
    }

    //TODO: extract property
    @Async
    @RabbitListener(queues = "${queue.validation}")
    public void consume(String payload) {

        log.info("Consuming VALIDATION_QUEUE with payload: {}", payload);

        try {
            log.info("Converting payload into a message");
            RegexWhitelistMessage message = this.gson.fromJson(payload, RegexWhitelistMessage.class);

        } catch (Exception e){
            log.error("An error has been occurred while trying to convert payload from message queue", e);
        }
    }
}
