package br.com.omnilabs.urlanalyser.repository;

import br.com.omnilabs.urlanalyser.model.ClientRegexWhitelist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegexWhitelistRepository extends CrudRepository<ClientRegexWhitelist, Integer> {
}
