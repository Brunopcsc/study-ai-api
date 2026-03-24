package com.bruno.study.ai.exam_preparation_ai_backend.repository;


import com.bruno.study.ai.exam_preparation_ai_backend.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

}
