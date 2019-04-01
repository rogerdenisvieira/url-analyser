package br.com.omnilabs.urlanalyser.service;

import br.com.omnilabs.urlanalyser.model.Regex;
import br.com.omnilabs.urlanalyser.model.ValidationResult;
import br.com.omnilabs.urlanalyser.repository.RegexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ValidationService {

    private RegexRepository regexRepository;

    @Autowired
    public ValidationService(RegexRepository regexRepository) {
        this.regexRepository = regexRepository;
    }

    /**
     * If there are regular expressions for provided client, then applies them and also all global
     * regular expressions. Otherwise, applies just global regular expressions.
     *
     * @param client a key to search for client's regular expressions
     * @param url a value that will be validated against a regular expression
     * @param correlationId an identifier of original request
     * @return a object ValidationResult that contains whether a regex matches or not, its value and
     * a correlationId to identify its original request
     */

    public ValidationResult validateUrl(String client, String url, Integer correlationId) {

        if (regexRepository.existsByClient(client)) {
            List<Regex> regexList = this.regexRepository.findByClientOrIsGlobal(client, Boolean.TRUE);

            log.info("Applying {} global/custom regexes of client [{}] against URL [{}]", regexList.size(), client, url);
            return checkUrlInRegexWhitelist(regexList, url, correlationId);

        } else {
            List<Regex> regexList = regexRepository.findByIsGlobal(Boolean.TRUE);

            log.info("There is no regexes for client [{}]. Applying {} global regexes against URL [{}]", client,regexList.size(), url);
            return checkUrlInRegexWhitelist(regexList, url, correlationId);
        }
    }

    private ValidationResult checkUrlInRegexWhitelist(List<Regex> regexList, String url, Integer correlationId) {

            for (Regex regex : regexList) {
                if (url.matches(regex.getRegex())) {
                    log.info("URL [{}] matches for regex: [{}]. Returning validation result.", url, regex.getRegex());
                    return new ValidationResult(Boolean.TRUE, regex.getRegex(), correlationId);
                }
                log.info("URL [{}] doesn't match for regex: [{}]", url, regex.getRegex());
            }
        return new ValidationResult(Boolean.FALSE, null, correlationId);
    }
}
