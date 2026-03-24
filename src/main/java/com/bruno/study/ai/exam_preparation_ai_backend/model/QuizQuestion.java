package com.bruno.study.ai.exam_preparation_ai_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_quiz_question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "question_a")
    private String questionA;

    @Column(name = "question_b")
    private String questionB;

    @Column(name = "question_c")
    private String questionC;

    @Column(name = "question_d")
    private String questionD;

    @Column(name = "question_e")
    private String questionE;

    @Column(name = "correct_option")
    private String correctOption;

    @Column()
    private String explanation;

}