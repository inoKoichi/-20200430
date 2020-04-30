package com.example.demo.Service;

import java.util.Objects;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * ユーザー情報サービスクラス
 * 
 * @author dc_haruka
 */
@Service
@RequiredArgsConstructor
@Setter
@Getter
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * ユーザー情報登録処理
   * 
   * @param userId
   * @param userName
   * @param loginId
   * @param loginPass
   */
  public void regist(String userName, String loginId, String loginPass, int role) {
    Integer userId = null;
    if (Objects.isNull(userId)) {
      User user = new User();
      user.setUserName(userName);
      user.setLoginId(loginId);
      user.setLoginPass(loginPass);
      user.setRole(role);
      userRepository.save(user);
    }
  }
}
