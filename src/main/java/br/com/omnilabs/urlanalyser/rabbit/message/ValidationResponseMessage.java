package br.com.omnilabs.urlanalyser.rabbit.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

public class ValidationResponseMessage implements Serializable {

    @NonNull
    private Boolean match;

    @Nullable
    private String regex;

    @NonNull
    private Integer correlationId;

    public ValidationResponseMessage(Boolean match, @Nullable String regex, Integer correlationId) {
        this.match = match;
        this.regex = regex;
        this.correlationId = correlationId;
    }

    public ValidationResponseMessage() {
    }

    public Boolean getMatch() {
        return match;
    }

    @Nullable
    public String getRegex() {
        return regex;
    }

    public Integer getCorrelationId() {
        return correlationId;
    }

    @Override
    public String toString() {
        return "ValidationResponseMessage{" +
                "match=" + match +
                ", regex='" + regex + '\'' +
                ", correlationId=" + correlationId +
                '}';
    }
}
