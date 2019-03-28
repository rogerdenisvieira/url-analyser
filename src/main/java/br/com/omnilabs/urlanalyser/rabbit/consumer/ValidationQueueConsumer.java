package br.com.omnilabs.urlanalyser.rabbit.consumer;

import br.com.omnilabs.urlanalyser.config.RabbitConfig;
import br.com.omnilabs.urlanalyser.model.ValidationResult;
import br.com.omnilabs.urlanalyser.rabbit.message.ValidationRequestMessage;
import br.com.omnilabs.urlanalyser.rabbit.message.ValidationResponseMessage;
import br.com.omnilabs.urlanalyser.rabbit.producer.ValidationResponseProducer;
import br.com.omnilabs.urlanalyser.service.UrlValidationService;
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


    private ValidationResponseProducer validationResponseProducer;
    private UrlValidationService urlValidationService;
    private Gson gson;

    @Autowired
    public ValidationQueueConsumer(ValidationResponseProducer validationResponseProducer, UrlValidationService urlValidationService, Gson gson) {
        this.validationResponseProducer = validationResponseProducer;
        this.urlValidationService = urlValidationService;
        this.gson = gson;
    }


    @RabbitListener(queues = "${validation.queue}")
    public void consume(String payload) {

        log.info("Consuming VALIDATION_QUEUE with payload: {}", payload);

        try {
            log.info("Converting payload into a message");
            ValidationRequestMessage validationRequestMessage = this.gson.fromJson(payload, ValidationRequestMessage.class);

            ValidationResult validationResult = this.urlValidationService.validateUrl(validationRequestMessage.getClient(), validationRequestMessage.getUrl(), validationRequestMessage.getCorrelationId());

            this.validationResponseProducer.send(
                    new ValidationResponseMessage(
                            validationResult.getMatch(),
                            validationResult.getRegex(),
                            validationResult.getCorrelationId()
                    )
            );

        } catch (Exception e) {
            log.error("An error has been occurred while trying to convert payload from message queue", e);
        }
    }
}
