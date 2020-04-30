package com.example.demo.Repository;

import com.example.demo.Entity.Questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * questionRepository
 * DB access
 */
public interface QuestionRepository extends JpaRepository<Questions, Long> {

  Questions findByQuestionIdEquals(Integer questionId);
}
