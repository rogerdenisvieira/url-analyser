package br.com.omnilabs.urlanalyser.rabbit.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.io.Serializable;


public class ValidationRequestMessage implements Serializable {

    @NonNull
    private String client;

    @NonNull
    private String url;

    @NonNull
    private Integer correlationId;

    public ValidationRequestMessage(String client, String url, Integer correlationId) {
        this.client = client;
        this.url = url;
        this.correlationId = correlationId;
    }

    public ValidationRequestMessage() {
    }

    public String getClient() {
        return client;
    }

    public String getUrl() {
        return url;
    }

    public Integer getCorrelationId() {
        return correlationId;
    }

    @Override
    public String toString() {
        return "ValidationRequestMessage{" +
                "client='" + client + '\'' +
                ", url='" + url + '\'' +
                ", correlationId=" + correlationId +
                '}';
    }
}
