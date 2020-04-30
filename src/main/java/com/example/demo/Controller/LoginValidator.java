package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Form.NewRegistForm;
import com.example.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * ログインIDの重複チェック 重複している場合エラー
 * 
 * @author dc_haruka
 */
@Component
public class LoginValidator implements Validator {

  @Autowired
  /**
   * UserDao
   */
  private UserRepository userRepository;

  /**
   * オブジェクトのチェック
   */
  public boolean supports(Class<?> clazz) {
    return NewRegistForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasFieldErrors("loginId")) {
      return;
    }
    NewRegistForm form = (NewRegistForm) target;
    if (form != null) {
      String loginId = form.getLoginId();
      String loginPass = form.getLoginPass();

      User check = userRepository.findByLoginIdAndLoginPass(loginId, loginPass);
      String checkId = check.getLoginId();
      String password = check.getLoginPass();

      if (checkId.equals(loginId) && password.equals(loginPass)) {

      } else {
        errors.rejectValue("loginId", "validator.LoginError");
      }
    }
  }

}
