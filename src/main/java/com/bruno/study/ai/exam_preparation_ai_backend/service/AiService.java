package com.bruno.study.ai.exam_preparation_ai_backend.service;

import com.bruno.study.ai.exam_preparation_ai_backend.dto.StudyContentBody;
import com.bruno.study.ai.exam_preparation_ai_backend.dto.StudyContentRecord;
import com.bruno.study.ai.exam_preparation_ai_backend.dto.TopicQuizRecord;
import com.bruno.study.ai.exam_preparation_ai_backend.model.QuizQuestion;
import com.bruno.study.ai.exam_preparation_ai_backend.model.StudyContent;
import com.bruno.study.ai.exam_preparation_ai_backend.repository.QuizQuestionRepository;
import com.bruno.study.ai.exam_preparation_ai_backend.repository.StudyContentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AiService {

    private final ChatModel chatModel;
    private final StudyContentRepository studyContentRepository;
    private final QuizQuestionRepository quizQuestionRepository;

    @Transactional
    public StudyContent saveStudyMaterial(StudyContentRecord studyContentRecord,String topic, UUID userId) {
        StudyContent studyContent = new StudyContent();

        studyContent.setTopicName(studyContentRecord.topic());
        studyContent.setSummaryMarkdown(studyContentRecord.summary());
        studyContent.setKeyPoints(studyContentRecord.keyPoints());
        studyContent.setUserId(userId);

        return studyContentRepository.save(studyContent);
    }

    @Transactional
    public List<QuizQuestion> saveQuizForContent(UUID contentId) {
        StudyContent content = studyContentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        TopicQuizRecord aiQuiz = generateQuiz(content.getTopicName(), content.getSummaryMarkdown());

        List<QuizQuestion> questions = aiQuiz.questions().stream().map(q -> {
            QuizQuestion entity = new QuizQuestion();

            entity.setQuestionText(q.question());
            entity.setOptions(q.options());
            entity.setCorrectAnswerIndex(q.correctAnswerIndex());
            entity.setExplanation(q.explanation());
            entity.setStudyContent(content);

            return entity;
        }).toList();

        return quizQuestionRepository.saveAll(questions);
    }

    public StudyContentRecord generateStudyContent(String topic, UUID userId) {
        var converter = new BeanOutputConverter<>(StudyContentRecord.class);

        String templateText = """
                Act as an expert tutor for Civil Service Exams (Concursos públicos).
                Generate a high-level STUDY SUMMARY about: {topic}.
                Focus on Portuguese language, mnemonics, and exam tips.
                The 'summary' must be in Markdown.
                {format}
                """;

        Map<String, Object> model = Map.of(
                "topic", topic,
                "format", converter.getFormat()
        );

        PromptTemplate promptTemplate = new PromptTemplate(templateText);
        Prompt prompt = promptTemplate.create(model);

        ChatResponse response = chatModel.call(prompt);
        String rawJson = response.getResult().getOutput().getText();
        StudyContentRecord studyContentRecord = converter.convert(rawJson);
        saveStudyMaterial(studyContentRecord, topic, userId);

        return studyContentRecord;
    }

    public TopicQuizRecord generateQuiz(String topic, String existingSummary) {
        var converter = new BeanOutputConverter<>(TopicQuizRecord.class);

        String templateText = """
                Based on the following study summary: "{existingSummary}"
                Generate 5 challenging multiple-choice questions in Portuguese.
                Follow the exam patterns for the topic: {topic}.
                {format}
                """;

        Map<String, Object> model = Map.of(
                "existingSummary", existingSummary,
                "topic", topic,
                "format", converter.getFormat()
        );

        PromptTemplate promptTemplate = new PromptTemplate(templateText);
        Prompt prompt = promptTemplate.create(model);

        ChatResponse response = chatModel.call(prompt);
        String rawJson = response.getResult().getOutput().getText();

        return converter.convert(rawJson);
    }
}