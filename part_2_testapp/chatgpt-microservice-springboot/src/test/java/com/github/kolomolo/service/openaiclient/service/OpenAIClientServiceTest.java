package com.github.kolomolo.service.openaiclient.service;

import com.github.kolomolo.service.openaiclient.model.request.ChatRequest;
import com.github.kolomolo.service.openaiclient.model.request.TranscriptionRequest;
import com.github.kolomolo.service.openaiclient.model.response.ChatGPTResponse;
import com.github.kolomolo.service.openaiclient.model.response.WhisperTranscriptionResponse;
import com.github.kolomolo.service.openaiclient.openaiclient.OpenAIClient;
import com.github.kolomolo.service.openaiclient.openaiclient.OpenAIClientConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OpenAIClientServiceTest {

    @Mock
    private OpenAIClientConfig config;

    @Mock
    private OpenAIClient openAIClient;

    @InjectMocks
    private OpenAIClientService openAIClientService;

    @Test
    void chat_ValidChatRequest_ReturnsChatGPTResponse() {
        ChatRequest chatRequest = new ChatRequest("test question");
        when(openAIClient.chat(any())).thenReturn(new ChatGPTResponse());
        ChatGPTResponse response = openAIClientService.chat(chatRequest);
        assertNotNull(response);
        verify(openAIClient, times(1)).chat(any());
    }

    @Test
    void createTranscription_ValidTranscriptionRequest_ReturnsWhisperTranscriptionResponse() {
        TranscriptionRequest transcriptionRequest = new TranscriptionRequest();
        when(config.getAudioModel()).thenReturn("testAudioModel");
        when(openAIClient.createTranscription(any())).thenReturn(new WhisperTranscriptionResponse());
        WhisperTranscriptionResponse response = openAIClientService.createTranscription(transcriptionRequest);
        assertNotNull(response);
        verify(openAIClient, times(1)).createTranscription(any());
    }
}