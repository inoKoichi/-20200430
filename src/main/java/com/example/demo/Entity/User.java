package com.example.demo.Entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "usr_tbl")
@NoArgsConstructor
/**
 * ユーザー Entity
 */
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  Integer userId;

  @Column(name = "user_name")
  String userName;

  @Column(name = "login_id")
  String loginId;

  @Column(name = "login_pass")
  String loginPass;

  @Column(name = "role")
  Integer role;
}