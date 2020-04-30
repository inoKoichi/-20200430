package com.example.demo.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CharactersEnum {

  エクソシスト(1, "エクソシスト"), 教団員(2, "教団員"), ノア(3, "ノア"), その他(4, "その他");

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
    for (CharactersEnum character : values()) {
      if (character.getCode() == key) {
        return character.getValue();
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
  public static CharactersEnum getCharactersEnum(int key) {
    for (CharactersEnum character : values()) {
      if (character.getCode() == key) {
        return character;
      }
    }
    return null;
  }
}
