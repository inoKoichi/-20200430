package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "questions")
/**
 * 質問 Entity
 */
public class Questions {

  @Id
  @Column(name = "question_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer questionId;

  @Column(name = "choices")
  private String choices;

  @Column(name = "groups")
  private Integer groups;

}