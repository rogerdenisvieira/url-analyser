package br.com.omnilabs.urlanalyser.factory;

import br.com.omnilabs.urlanalyser.strategy.RegexWhitelistStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RegexWhitelistStrategyFactory {

    private List<RegexWhitelistStrategy> strategies;

    @Autowired
    public RegexWhitelistStrategyFactory(List<RegexWhitelistStrategy> strategies) {
        this.strategies = strategies;
    }

    public RegexWhitelistStrategy getStrategy(String client, String regex){
        log.info("Finding a suitable regext whitelist strategy");
        return this.strategies.stream().filter(s -> s.isASuitableStrategy(client, regex)).findFirst().get();
    }

}
