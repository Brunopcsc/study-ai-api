package com.bruno.study.ai.exam_preparation_ai_backend.dto;

import java.util.List;

public record TopicQuizRecord(
        String topic,
        List<QuizQuestionRecord> questions
) {
}