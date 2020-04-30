package com.example.demo.Form;

import lombok.Getter;
import lombok.Setter;

/**
 * 新規登録用のフォーム
 */
@Getter
@Setter
public class NewRegistForm {

  private Integer userId;
  private String userName;
  private String loginId;
  private String loginPass;

}