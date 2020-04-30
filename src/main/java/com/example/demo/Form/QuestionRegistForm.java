package com.example.demo.Form;

import lombok.Getter;
import lombok.Setter;

/**
 * 質問登録用フォーム
 */
@Getter
@Setter
public class QuestionRegistForm {

  private Integer questionId;
  private String choices;
  private Integer groups;
  private boolean regist;
}
