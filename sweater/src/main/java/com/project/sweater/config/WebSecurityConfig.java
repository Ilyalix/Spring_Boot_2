package com.project.sweater.config;

import com.project.sweater.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // не знаю зачем, но очень важная строчка! (для прав пользов.)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; // получения dataSource для доступа к БД

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // передаем на вход объект HttpSecurity http
        http
                .authorizeRequests() // включаем авторизацию
                    .antMatchers( "/registration").permitAll() // на главную страницу разрешаем доступ
                    .anyRequest().authenticated()  // на все остальные требуем авторизацию
                .and()
                    .formLogin() //включаем форму логин
                    .loginPage("/login") // находится на меппинге /login
                    .permitAll() // разрешаем пользоваться всем
                .and()
                    .logout() //включаем форму лог-out
                    .permitAll(); // разрешаем пользоваться всем
    }

//    @Override // здесь мы берем данные из БД (переопределяем метод Configure Alt-Ins)
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.jdbcAuthentication()
//     .dataSource(dataSource)  // менеджер заходит в БД и добавляет роли
//     .passwordEncoder(NoOpPasswordEncoder.getInstance()) // шифрование паролей
//     // запросы
//     // чтобы система могла найти пользователя по его имени username. то есть
//    .usersByUsernameQuery("select username, password, active from user where username=?")
//     // получение списка пользователя с их ролями, то есть
//     // из таблицы User и присоединенной к ней user_role, соединенной через поля id и user_id,выбираем поля username и имя roles
//    .authoritiesByUsernameQuery("select u.username, ur.roles from user u inner join user_role ur on u.id = ur.user_id where u.username=?");
//    }

    @Override // здесь мы берем данные из БД (переопределяем метод Configure Alt-Ins)
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder); // шифрование паролей
    }
}