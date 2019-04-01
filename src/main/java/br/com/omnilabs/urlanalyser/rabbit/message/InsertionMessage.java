package br.com.omnilabs.urlanalyser.rabbit.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

public class InsertionMessage implements Serializable {

    @Nullable
    @JsonProperty("client")
    private String client;

    @NonNull
    @JsonProperty("regex")
    private String regex;

    public InsertionMessage() {
    }


    public InsertionMessage(String client, String regex) {
        this.client = client;
        this.regex = regex;
    }

    public String getClient() {
        return client;
    }

    public String getRegex() {
        return regex;
    }

    @Override
    public String toString() {
        return "InsertionMessage{" +
                "client='" + client + '\'' +
                ", regex='" + regex + '\'' +
                '}';
    }
}
