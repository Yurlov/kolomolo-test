package com.github.kolomolo.service.openaiclient.model.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Builder
public class WhisperTranscriptionRequest implements Serializable {

    private String model;
    private MultipartFile file;
}
