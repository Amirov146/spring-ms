package com.example.main.security;

import com.example.main.service.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    class LoginPageFilter extends GenericFilterBean {


        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if (SecurityContextHolder.getContext().getAuthentication() != null
                    && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                    && ((HttpServletRequest)request).getRequestURI().equals("/")) {
                System.out.println("user is authenticated but trying to access login page, redirecting to /main");
                ((HttpServletResponse)response).sendRedirect("/main");
            }
            chain.doFilter(request, response);
        }
    }

    private UserServiceDetails userService;

    @Autowired
    public void setUserService(UserServiceDetails userService) {
        this.userService = userService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/admin-panel","admin-panel/css", "admin-panel/js"
                , "/find-user", "/delete-movie","/all-movies","/add-movie").hasAuthority("ADMIN")
                .antMatchers("/main/**","main/css/**","main/icon/**","main/images/**","main/js/**",
                "/movie/**","movie/css","movie/images","movie/videos",
                        "/test/**","/user","user/css/**","user/images/**").hasAuthority("USER")
                .antMatchers("/","index/css/**","index/icon/**","index/js/**","index/images/**"
                ,"/register","site/cs","site/images/**","reg/js/**","reg/css/**","reg/images/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/main", true)
                .and().authorizeRequests().antMatchers("/").not().authenticated()
                .and()
                .logout()
                .logoutUrl("/main")
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

//    // Successful Log In alert
//    public class LoginSuccessHandler implements AuthenticationSuccessHandler {
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//            response.getWriter().append("<script>alert('Login was successful!');</script>");
//        }
//    }
}

