package br.com.omnilabs.urlanalyser.rabbit.consumer;

import br.com.omnilabs.urlanalyser.factory.RegexWhitelistStrategyFactory;
import br.com.omnilabs.urlanalyser.rabbit.message.RegexWhitelistMessage;
import br.com.omnilabs.urlanalyser.config.MessageQueueConfig;
import br.com.omnilabs.urlanalyser.strategy.RegexWhitelistStrategy;
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
public class InsertionQueueConsumer {

    private RegexWhitelistStrategyFactory factory;
    private MessageQueueConfig config;
    private RabbitTemplate rabbitTemplate;
    private Queue queue;
    private Gson gson;

    @Autowired
    public InsertionQueueConsumer(RegexWhitelistStrategyFactory factory, MessageQueueConfig config,
                                  RabbitTemplate rabbitTemplate,
                                  @Qualifier("insertionQueue") Queue queue,
                                  Gson gson) {
        this.factory = factory;

        this.config = config;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.gson = gson;
    }

    //TODO: extract property

    @Async
    @RabbitListener(queues = "${queue.insertion}")
    public void consume(String payload) {
        log.info("Consuming INSERTION_QUEUE with payload: {}", payload);

        try {
            log.info("Converting payload into a message");
            RegexWhitelistMessage message = this.gson.fromJson(payload, RegexWhitelistMessage.class);

            RegexWhitelistStrategy strategy = factory.getStrategy(message.getClient(), message.getRegex());

            strategy.handleRegexWhitelist(message.getClient(), message.getRegex());


        } catch (Exception e) {
            log.error("An error has been occurred while trying to convert payload from message queue", e);
        }
    }
}
