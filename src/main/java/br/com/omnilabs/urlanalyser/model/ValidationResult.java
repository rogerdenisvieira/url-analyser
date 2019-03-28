package br.com.omnilabs.urlanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class ValidationResult {

    @NonNull
    private Boolean match;

    @Nullable
    private String regex;

    @NonNull
    private Integer correlationId;
}