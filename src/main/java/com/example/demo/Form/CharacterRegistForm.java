package com.example.demo.Form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CharacterRegistForm {

  private Integer characterId;

  private String characterName;

  private String birthday;

  private Integer gender;

  private Integer memberOf;

  private String weapon;

  private String nationality;

  private String height;

  private String fav;

  private String dislike;

  private boolean regist;

}
