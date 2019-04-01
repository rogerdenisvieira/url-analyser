package br.com.omnilabs.urlanalyser.rabbit.consumer;

import br.com.omnilabs.urlanalyser.model.ValidationResult;
import br.com.omnilabs.urlanalyser.rabbit.message.ValidationRequestMessage;
import br.com.omnilabs.urlanalyser.rabbit.message.ValidationResponseMessage;
import br.com.omnilabs.urlanalyser.rabbit.producer.ValidationResponseProducer;
import br.com.omnilabs.urlanalyser.service.ValidationService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class ValidationConsumer {


    private ValidationResponseProducer validationResponseProducer;
    private ValidationService validationService;

    @Autowired
    public ValidationConsumer(ValidationResponseProducer validationResponseProducer, ValidationService validationService) {
        this.validationResponseProducer = validationResponseProducer;
        this.validationService = validationService;

    }


    @RabbitListener(queues = "${validation.queue}")
    public void consume(ValidationRequestMessage validationRequestMessage) {


        try {
            log.info("Received message from queue VALIDATION_QUEUE with content: {}", validationRequestMessage);

            ValidationResult validationResult = this.validationService.validateUrl(validationRequestMessage.getClient(), validationRequestMessage.getUrl(), validationRequestMessage.getCorrelationId());

            log.info("Sending validation result to exchange RESPONSE_EXCHANGE");
            this.validationResponseProducer.send(
                    new ValidationResponseMessage(
                            validationResult.getMatch(),
                            validationResult.getRegex(),
                            validationResult.getCorrelationId()
                    )
            );

        } catch (Exception e) {
            log.error("An error has been occurred while trying to send validation result", e);
        }
    }
}
