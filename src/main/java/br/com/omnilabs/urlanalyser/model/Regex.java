package br.com.omnilabs.urlanalyser.model;

import com.mysql.cj.util.StringUtils;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "regex_whitelist")
public class Regex {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String client;

    @NonNull
    private String regex;

    private Boolean isGlobal;


    public Regex(){}

    public Regex(String regex) {
        this.regex = regex;
        this.isGlobal = Boolean.TRUE;
    }

    public Regex(String client, String regex) {
        this.client = client;
        this.regex = regex;
        this.isGlobal = StringUtils.isNullOrEmpty(client);
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

    public Boolean getGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }
}
