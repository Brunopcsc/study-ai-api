package com.bruno.study.ai.exam_preparation_ai_backend.dto;

import java.util.List;

public record QuizQuestionRecord(
        String question,
        List<String> options,
        int correctAnswerIndex,
        String explanation
) {}