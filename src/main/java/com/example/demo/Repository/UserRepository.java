package com.example.demo.Repository;

import com.example.demo.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/***
 * userRepository
 * DB access
 */
public interface UserRepository extends JpaRepository<User, Long> {

  //ログインIDが一致しているものを取得
  User findByLoginIdEquals(String loginId);

  //ログインIDとパスワードで取得
  User findByLoginIdAndLoginPass(String loginId, String loginPass);
}