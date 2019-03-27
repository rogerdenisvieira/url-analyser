package br.com.omnilabs.urlanalyser.rabbit.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class ValidationResponseMessage {

    @NonNull
    private Boolean match;

    @Nullable
    private String regex;

    @NonNull
    private Integer correlationId;
}
