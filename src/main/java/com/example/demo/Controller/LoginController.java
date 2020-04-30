package com.example.demo.Controller;

import java.util.Set;

import com.example.demo.Bean.LoginUser;
import com.example.demo.Form.NewRegistForm;
import com.example.demo.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/**
 * ログイン処理
 */
public class LoginController {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  /**
   * 初期画面表示処理
   * 
   * @param loginUser
   * @param model
   * @return
   */
  @RequestMapping("/")
  public String index(@AuthenticationPrincipal LoginUser loginUser, Model model) {

    if (loginUser != null) {
      Set<String> set = AuthorityUtils.authorityListToSet(loginUser.getAuthorities());
      if (set.contains("ROLE_ADMIN")) {
        return "redirect:/admin";
      }
      return "redirect:/index";
    }
    return "login";
  }

  /**
   * ログインパスが通った時
   * 
   * @return
   */
  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  /**
   * パスワードハッシュ化
   */
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  /**
   * 新規登録画面の表示処理
   * 
   * @param model
   * @return
   */
  @RequestMapping("/register")
  public String register(Model model) {
    NewRegistForm form = new NewRegistForm();
    model.addAttribute("registerForm", form);
    return "register";
  }

  /**
   * 新規登録処理
   * 
   * @param loginId
   * @param userName
   * @param loginPass
   * @param model
   * @return
   */
  @RequestMapping("/new_register")
  @Transactional
  public String regist(String loginId, String userName, String loginPass, int role, Model model) {
    if (userName.isEmpty() || loginId.isEmpty() || loginPass.isEmpty()) {
      return "error";
    }

    // ハッシュ化パスワード取得
    String hash = passwordEncoder.encode(loginPass);
    userService.regist(userName, loginId, hash, role);
    return "redirect:/?id=&status=success";
  }

  public boolean matches(String loginPass, String hash) {
    return passwordEncoder.matches(loginPass, hash);
  }

}