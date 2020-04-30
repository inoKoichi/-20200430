package com.example.demo.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 質問情報　Enum
 */
public enum QuestionsGroupsEnum {

  A(1, "奇数ID"), B(2, "偶数ID"), C(3, "memberOf1+2"), D(4, "memberOf3+4"), E(5, "memberOf1+3"), F(6, "memberOf2+4");

  /**
   * 番号
   */
  private int code;

  /**
   * 値
   */
  private String value;

  /**
   * 引数に入った値(key)と一致するvalueを取得する
   * 
   * @param key
   * @return
   */
  public static String getValue(int key) {
    for (QuestionsGroupsEnum group : values()) {
      if (group.getCode() == key) {
        return group.getValue();
      }
    }
    return "";
  }

  /**
   * サービスで文字列を呼び出す用のメソッドを作成
   * 
   * @param key
   * @return
   */
  public static QuestionsGroupsEnum getQuestionsGroupsEnum(int key) {
    for (QuestionsGroupsEnum group : values()) {
      if (group.getCode() == key) {
        return group;
      }
    }
    return null;
  }
}
