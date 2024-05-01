package com.github.kolomolo.service.openaiclient.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhisperTranscriptionResponse implements Serializable {
    private String text;
}
