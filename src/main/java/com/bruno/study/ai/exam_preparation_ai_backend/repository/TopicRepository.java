package com.bruno.study.ai.exam_preparation_ai_backend.repository;


import com.bruno.study.ai.exam_preparation_ai_backend.model.Topic;
import com.bruno.study.ai.exam_preparation_ai_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<List<User>> findByUserId(Long userId);

}
