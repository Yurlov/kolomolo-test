package com.github.kolomolo.service.openaiclient.model.response;

import com.github.kolomolo.service.openaiclient.model.request.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice implements Serializable {
    private Integer index;
    private Message message;
    private String finishReason;
}
