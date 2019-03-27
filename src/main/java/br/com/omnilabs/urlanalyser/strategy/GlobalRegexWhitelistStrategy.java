package br.com.omnilabs.urlanalyser.strategy;

import br.com.omnilabs.urlanalyser.model.ClientRegexWhitelist;
import br.com.omnilabs.urlanalyser.model.GlobalRegexWhitelist;
import br.com.omnilabs.urlanalyser.repository.GlobalRegexWhitelistRepository;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GlobalRegexWhitelistStrategy implements RegexWhitelistStrategy {

    private GlobalRegexWhitelistRepository globalRegexWhitelistRepository;

    @Autowired
    public GlobalRegexWhitelistStrategy(GlobalRegexWhitelistRepository globalRegexWhitelistRepository) {
        this.globalRegexWhitelistRepository = globalRegexWhitelistRepository;
    }

    @Override
    public void handleRegexWhitelist(String client, String regex) {
        try {
            log.info("Client is null or empty. Saving regular expression into global whitelist.");
            globalRegexWhitelistRepository.save(new GlobalRegexWhitelist(regex));
        } catch (Exception e) {
            log.error("An error occurred while trying to save regex.");
        }
    }

    @Override
    public Boolean isASuitableStrategy(String client, String regex) {
        return StringUtils.isNullOrEmpty(client);
    }
}
