package com.example.demo.Config;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.AuthenticationException;

import com.example.demo.Bean.LoginUser;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configurable
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
/**
 * Spring Security
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/css/**", "/js/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // 認証が必要となるURLの設定
        http.authorizeRequests()
                // ログインページは認証不要
                .antMatchers("/", "/login", "/new_register", "/register").permitAll()
                // それ以外は認証が必要
                .anyRequest().authenticated();
        http.csrf().disable().formLogin()
                // ログインフォームのパス
                .loginPage("/login")
                // 失敗時
                .failureUrl("/login?error")
                // ログインフォームのinput name
                .usernameParameter("loginId")
                // パスワードのinput name
                .passwordParameter("login_pass")
                // 以下のURLにリクエストが送られると認証
                .loginProcessingUrl("/login")
                // 成功時
                .defaultSuccessUrl("/", true).permitAll().and()
                // セッション有効時間の指定
                .rememberMe().tokenValiditySeconds(172800);
        http.logout()
                // ログアウトパス設定
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // logout後遷移先
                .logoutSuccessUrl("/")
                // セッション破棄
                .invalidateHttpSession(true).permitAll();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        return new UserDetailsAuthenticationProvider();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

        @Override
        protected void additionalAuthenticationChecks(final UserDetails userDetails,
                final UsernamePasswordAuthenticationToken authentication) {
        }

        @Override
        protected UserDetails retrieveUser(final String loginId,
                final UsernamePasswordAuthenticationToken authentication) {

            User user = userRepository.findByLoginIdEquals(loginId);
            String loginPass = (String) authentication.getCredentials();

            Integer role = user.getRole();
            int userId = user.getUserId();
            String userName = user.getUserName();
            // role setting
            List<String> roleList = new ArrayList<String>();
            if (role != null && role == 1) {
                roleList.add("ROLE_ADMIN");
            } else {
                // 入力されたPASSの値とDBに格納されているパスワードが一致しているか比較
                if (passwordEncoder.matches(loginPass, user.getLoginPass())) {
                }
                roleList.add("ROLE_USER");
            }
            return new LoginUser(loginId, user.getLoginPass(), userId, userName, roleList);
        }
    }
}