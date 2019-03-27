package br.com.omnilabs.urlanalyser.strategy;

import br.com.omnilabs.urlanalyser.model.ClientRegexWhitelist;
import br.com.omnilabs.urlanalyser.repository.ClientRegexWhitelistRepository;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientRegexWhitelistStrategy implements RegexWhitelistStrategy {

    private ClientRegexWhitelistRepository clientRegexWhitelistRepository;

    @Autowired
    public ClientRegexWhitelistStrategy(ClientRegexWhitelistRepository clientRegexWhitelistRepository) {
        this.clientRegexWhitelistRepository = clientRegexWhitelistRepository;
    }

    @Override
    public void handleRegexWhitelist(String client, String regex) {
        try {
            log.info("Saving regular expression for client {}", client);
            clientRegexWhitelistRepository.save(new ClientRegexWhitelist(client, regex));
        } catch (Exception e) {
            log.error("An error occurred while trying to save regex.");
        }
    }

    @Override
    public Boolean isASuitableStrategy(String client, String regex) {
        return !StringUtils.isNullOrEmpty(client);
    }
}
