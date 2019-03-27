package br.com.omnilabs.urlanalyser.model;

import javax.persistence.*;

@Entity
@Table(name = "client_regex_whitelist")
public class ClientRegexWhitelist {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String client;
    private String regex;

    public Integer getId() {
        return id;
    }

    public ClientRegexWhitelist(String client, String regex) {
        this.client = client;
        this.regex = regex;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
