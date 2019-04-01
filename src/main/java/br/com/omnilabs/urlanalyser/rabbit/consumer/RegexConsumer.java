package br.com.omnilabs.urlanalyser.rabbit.consumer;

import br.com.omnilabs.urlanalyser.rabbit.message.InsertionMessage;
import br.com.omnilabs.urlanalyser.service.RegexService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RegexConsumer {


    private RegexService regexService;

    @Autowired
    public RegexConsumer(RegexService regexService) {
        this.regexService = regexService;
    }

    @Async
    @RabbitListener(queues = "${insertion.queue}")
    public void consume(InsertionMessage insertionMessage) {

            log.info("Received message from queue INSERTION_QUEUE with content: {}", insertionMessage);
            this.regexService.saveNewRegex(insertionMessage.getClient(), insertionMessage.getRegex());
    }
}
