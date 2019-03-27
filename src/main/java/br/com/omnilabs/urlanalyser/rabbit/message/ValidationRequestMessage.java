package br.com.omnilabs.urlanalyser.rabbit.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor
public class ValidationRequestMessage {

    @NonNull
    private String client;

    @NonNull
    private String url;

    @NonNull
    private Integer correlationId;

}
