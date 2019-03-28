package br.com.omnilabs.urlanalyser.repository;

import br.com.omnilabs.urlanalyser.model.Regex;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegexRepository extends CrudRepository<Regex, Integer> {

    List<Regex> findByIsGlobal(Boolean isGlobal);

    List<Regex> findByClientOrIsGlobal(String client, Boolean isGlobal);

    Boolean existsByClient(String client);

}
