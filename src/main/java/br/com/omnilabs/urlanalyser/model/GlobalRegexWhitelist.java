package br.com.omnilabs.urlanalyser.model;

import javax.persistence.*;

@Entity
@Table(name = "global_regex_whitelist")
public class GlobalRegexWhitelist {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String regex;

    public Integer getId() {
        return id;
    }

    public GlobalRegexWhitelist(String regex) {
        this.regex = regex;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
