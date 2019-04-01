package br.com.omnilabs.urlanalyser.rabbit.consumer;

import br.com.omnilabs.urlanalyser.rabbit.message.InsertionMessage;
import br.com.omnilabs.urlanalyser.service.RegexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegexConsumerTest {

    @Mock
    private RegexService regexService;

    @InjectMocks
    private RegexConsumer regexConsumer;

    @Test
    public void givenAMessage_thenSaveIt(){

        InsertionMessage message = new InsertionMessage("umClient", "umaRegex");
        regexConsumer.consume(message);
        verify(regexService).saveNewRegex(message.getClient(), message.getRegex());
    }
}