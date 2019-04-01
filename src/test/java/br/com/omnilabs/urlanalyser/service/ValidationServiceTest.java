package br.com.omnilabs.urlanalyser.service;

import br.com.omnilabs.urlanalyser.model.Regex;
import br.com.omnilabs.urlanalyser.model.ValidationResult;
import br.com.omnilabs.urlanalyser.repository.RegexRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {


    @Mock
    private RegexRepository regexRepository;

    @InjectMocks
    private ValidationService validationService;

    private String validationRegex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private List<Regex> regexes;

    @Before
    public void setup() {


        Regex regex = new Regex(validationRegex);
        List<Regex> regexes = new ArrayList<>();
        regexes.add(regex);

        when(regexRepository.existsByClient("xpto")).thenReturn(Boolean.FALSE);
        when(regexRepository.findByIsGlobal(Boolean.TRUE)).thenReturn(regexes);

        when(regexRepository.existsByClient("foobar")).thenReturn(Boolean.TRUE);
        when(regexRepository.findByClientOrIsGlobal("foobar", Boolean.TRUE)).thenReturn(regexes);

    }


    @Test
    public void givenAValidUrlForGlobalWhitelist_thenReturnAValidResult() {

        Integer correlationId = 1234;

        String url = "http://umsitebacana.com";

        ValidationResult result = validationService.validateUrl("xpto", url, correlationId);

        assertEquals(result.getMatch(), Boolean.TRUE);
        assertEquals(result.getRegex(), validationRegex);
        assertEquals(result.getCorrelationId(), correlationId);


    }

    @Test
    public void givenAnInvalidUrlForGlobalWhitelist_thenReturnAInvalidResult(){
        Integer correlationId = 1234;
        String url = "mandolate";

        ValidationResult result = validationService.validateUrl("xpto", url, correlationId);

        assertEquals(result.getMatch(), Boolean.FALSE);
        assertNull(result.getRegex());
        assertEquals(result.getCorrelationId(), correlationId);
    }

    @Test
    public void givenAValidUrlForClientWhitelist_thenReturnAValidResult(){
        Integer correlationId = 1234;
        String url = "http://umsitebacana.com";

        ValidationResult result = validationService.validateUrl("foobar", url, correlationId);

        assertEquals(result.getMatch(), Boolean.TRUE);
        assertEquals(result.getRegex(), validationRegex);
        assertEquals(result.getCorrelationId(), correlationId);
    }

    @Test
    public void givenAnInvalidUrlForClientWhitelist_thenReturnAnInvalidResult(){
        Integer correlationId = 1234;
        String url = "mandolate";

        ValidationResult result = validationService.validateUrl("foobar", url, correlationId);

        assertEquals(result.getMatch(), Boolean.FALSE);
        assertNull(result.getRegex());
        assertEquals(result.getCorrelationId(), correlationId);
    }


}