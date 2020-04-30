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
public class CharactersViewBean {

  private Integer characterId;

  private String characterName;

  private String birthday;

  private Integer gender;

  private CharactersEnum memberOf;

  private String weapon;

  private String nationality;

  private String height;

  private String fav;

  private String dislike;
}
