package br.com.omnilabs.urlanalyser.rabbit.consumer;

import br.com.omnilabs.urlanalyser.rabbit.message.RegexMessage;
import br.com.omnilabs.urlanalyser.service.RegexService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class InsertionQueueConsumer {


    private RegexService regexService;
    private Gson gson;

    @Autowired
    public InsertionQueueConsumer(RegexService regexService, Gson gson) {
        this.regexService = regexService;
        this.gson = gson;
    }

    @Async
    @RabbitListener(queues = "${insertion.queue}")
    public void consume(String payload) {
        log.info("Consuming INSERTION_QUEUE with payload: {}", payload);

        try {

            log.info("Converting payload into a message");
            RegexMessage message = this.gson.fromJson(payload, RegexMessage.class);
            this.regexService.saveNewRegex(message.getClient(), message.getRegex());

        } catch (Exception e) {
            log.error("An error has been occurred while trying to convert payload from message queue", e);
        }
    }
}
