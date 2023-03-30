package com.alsea.portal.portalmvc;

import com.alsea.portal.portalmvc.auth.LoginSuccessHandler;
import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/login","/css/**","/js/**","/images/**","/library/**","/tables/**").permitAll()
                .antMatchers("/propinas/**").hasAnyRole("USER_PROPINAS","ADMIN_PROPINAS","ADMIN")
                .antMatchers("/turnos/**").hasAnyRole("USER_TURNOS","ADMIN_TURNOS","ADMIN")
                .antMatchers("/upsize/**").hasAnyRole("USER_UPSIZE","ADMIN_UPSIZE","ADMIN")
                .antMatchers("/home").hasAnyRole("USER_PROPINAS","ADMIN_UPSIZE","ADMIN","ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .successHandler(successHandler)
                    .loginPage("/login")
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");

        //Se deben modificar accesos, funcionalidades de admin y user segun corresponda
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder(BCryptVersion.$2A);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{

        builder
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin")).roles("ADMIN").and()
                .withUser("gustavo")
                .password(passwordEncoder().encode("1234")).roles("USER_PROPINAS");
          }

}
