package br.com.omnilabs.urlanalyser.service;

import br.com.omnilabs.urlanalyser.model.Regex;
import br.com.omnilabs.urlanalyser.repository.RegexRepository;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
@Service
public class RegexService {

    private RegexRepository regexRepository;

    @Autowired
    public RegexService(RegexRepository regexRepository) {
        this.regexRepository = regexRepository;
    }

    public void saveNewRegex(String client, String regex) {
        try {

            String validRegex = Pattern.compile(regex).pattern();

            if (StringUtils.isNullOrEmpty(client)) {
                log.info("Client is null or empty. Saving regular expression into global whitelist.");
                this.regexRepository.save(new Regex(validRegex));
            } else {
                log.info("Saving regular expression for client [{}]", client);
                this.regexRepository.save(new Regex(client, validRegex));
            }

        } catch (PatternSyntaxException e) {
            log.error("Pattern [{}] is invalid.", regex);
        } catch (Exception e) {
            log.error("An error occurred while trying to save regex [{}].", regex);
        }
    }
}