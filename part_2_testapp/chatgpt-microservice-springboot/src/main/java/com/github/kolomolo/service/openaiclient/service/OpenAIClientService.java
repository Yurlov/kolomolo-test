package com.github.kolomolo.service.openaiclient.service;

import com.github.kolomolo.service.openaiclient.model.request.*;
import com.github.kolomolo.service.openaiclient.model.response.WhisperTranscriptionResponse;
import com.github.kolomolo.service.openaiclient.openaiclient.OpenAIClient;
import com.github.kolomolo.service.openaiclient.openaiclient.OpenAIClientConfig;
import com.github.kolomolo.service.openaiclient.model.response.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OpenAIClientService {

    private final OpenAIClientConfig config;

    private final OpenAIClient openAIClient;

    private static final String CHAT_ROLE = "user";

    public ChatGPTResponse chat(ChatRequest chatRequest){
        Message message = Message.builder()
                .role(CHAT_ROLE)
                .content(chatRequest.getQuestion())
                .build();
        return openAIClient.chat(
                new ChatGPTRequest(config.getModel(),
                        Collections.singletonList(message)));
    }

    public WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest){
        WhisperTranscriptionRequest whisperTranscriptionRequest = WhisperTranscriptionRequest.builder()
                .model(config.getAudioModel())
                .file(transcriptionRequest.getFile())
                .build();
        return openAIClient.createTranscription(whisperTranscriptionRequest);
    }
}
