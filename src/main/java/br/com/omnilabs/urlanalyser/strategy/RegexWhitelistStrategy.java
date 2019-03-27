package br.com.omnilabs.urlanalyser.strategy;

import org.springframework.stereotype.Component;

@Component
public interface RegexWhitelistStrategy {

    void handleRegexWhitelist(String client, String regex);

    Boolean isASuitableStrategy(String client, String regex);
}
