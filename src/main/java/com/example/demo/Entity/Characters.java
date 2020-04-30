package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "characters")
/**
 * キャラクター用Entity
 */
public class Characters {

  @Id
  @Column(name = "character_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer characterId;

  @Column(name = "character_name")
  private String characterName;
  @Column(name = "birthday")
  private String birthday;
  @Column(name = "gender")
  private Integer gender;
  @Column(name = "a_member_of")
  private Integer memberOf;
  @Column(name = "weapon")
  private String weapon;
  @Column(name = "nationality")
  private String nationality;
  @Column(name = "height")
  private String height;
  @Column(name = "fav")
  private String fav;
  @Column(name = "dislike")
  private String dislike;
}
