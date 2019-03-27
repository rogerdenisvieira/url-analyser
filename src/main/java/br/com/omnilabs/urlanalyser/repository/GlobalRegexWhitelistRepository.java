package br.com.omnilabs.urlanalyser.repository;

import br.com.omnilabs.urlanalyser.model.GlobalRegexWhitelist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalRegexWhitelistRepository extends CrudRepository<GlobalRegexWhitelist, Integer> {
}
