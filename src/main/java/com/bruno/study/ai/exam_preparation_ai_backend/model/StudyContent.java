package com.bruno.study.ai.exam_preparation_ai_backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_study_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyContent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "topic_name", nullable = false)
    private String topicName;

    @Column(name = "summary_markdown", nullable = false, columnDefinition = "TEXT")
    private String summaryMarkdown;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "key_points", columnDefinition = "jsonb")
    private List<String> keyPoints;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "studyContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizQuestion> questions;
}