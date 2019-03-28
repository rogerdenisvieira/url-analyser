package br.com.omnilabs.urlanalyser.rabbit.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@NoArgsConstructor
public class RegexMessage {

    @Nullable
    @JsonProperty("client")
    private String client;

    @NonNull
    @JsonProperty("regex")
    private String regex;


    public RegexMessage(String client, String regex) {
        this.client = client;
        this.regex = regex;
    }

    public String getClient() {
        return client;
    }

    public String getRegex() {
        return regex;
    }
}
