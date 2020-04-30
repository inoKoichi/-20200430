package com.example.demo.Bean;

import java.util.List;

import com.example.demo.Entity.User;

import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログインユーザー情報
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUser extends org.springframework.security.core.userdetails.User {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  private int userId;
  private String userName;

  public LoginUser(final String loginId, final String loginPass, final int userId, final String userName,
      final List<String> roles) {
    super(loginId, loginPass, AuthorityUtils.createAuthorityList(roles.toArray(new String[0])));

    this.userId = userId;
    this.userName = userName;
  }
}
