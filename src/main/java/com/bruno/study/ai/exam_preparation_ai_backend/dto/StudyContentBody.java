package com.bruno.study.ai.exam_preparation_ai_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StudyContentBody {

    private String topic;

    @JsonProperty("user_id")
    private UUID userId;
}
