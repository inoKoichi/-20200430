package com.example.demo.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 質問情報
 */
public class QuestionsBean {

  private Integer questionId;

  private String choices;

  private QuestionsGroupsEnum groups;
}
