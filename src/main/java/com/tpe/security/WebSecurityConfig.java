package com.tpe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration// to make this configuration class
@EnableWebSecurity//enabling security
@EnableMethodSecurity/*(prePostEnabled=true) is default*/ //security will be enabled in method base
public class WebSecurityConfig {
    /*
        1. AuthenticationManager
        2. AuthenticationProvider
        3. PassEncode
     */
    @Autowired
    private UserDetailsService userDetailService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //Disable Cross Site Request Forgery
                .authorizeHttpRequests(auth->{ //check requests if they are authorized
                    auth.requestMatchers("/","/index.html","/js/*","/css/*").permitAll();//give permission to these paths --> no authentication/authorization
                    auth.requestMatchers("/student")//except other requests
                            .hasRole("STUDENT");//authenticate
                    auth.requestMatchers("/admin")
                            .hasRole("ADMIN");
                    auth.requestMatchers("/teacher")
                            .hasRole("TEACHER");
                })
                .httpBasic(Customizer.withDefaults())//use Basic Authentication for this
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);//number between 4-31
        // (4 will be weakest, 31 is strongest (will take time, energy)
        // so normally middle around 10 is enough)
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider autoProvider = new DaoAuthenticationProvider();
        autoProvider.setPasswordEncoder(passwordEncoder());
        autoProvider.setUserDetailsService(userDetailService);
        return autoProvider;
    }

    @Bean
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }


}
