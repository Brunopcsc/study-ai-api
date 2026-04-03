package com.bruno.study.ai.exam_preparation_ai_backend.repository;


import com.bruno.study.ai.exam_preparation_ai_backend.model.StudyContent;
import com.bruno.study.ai.exam_preparation_ai_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudyContentRepository extends JpaRepository<StudyContent, UUID> {
    Optional<List<User>> findByUserId(UUID userId);

}
