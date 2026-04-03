package com.bruno.study.ai.exam_preparation_ai_backend.controller;

import com.bruno.study.ai.exam_preparation_ai_backend.dto.StudyContentBody;
import com.bruno.study.ai.exam_preparation_ai_backend.dto.StudyContentRecord;
import com.bruno.study.ai.exam_preparation_ai_backend.service.AiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
@AllArgsConstructor
public class GenerateStudyContentController {

    private final AiService aiService;

    @PostMapping("/generate-content")
    public ResponseEntity<StudyContentRecord> generateStudyContent(@RequestBody StudyContentBody studyContentBody) {
        StudyContentRecord studyContentRecord = aiService.generateStudyContent(studyContentBody.getTopic(), studyContentBody.getUserId());

        return ResponseEntity.ok(studyContentRecord);
    }
}
