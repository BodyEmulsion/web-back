package ru.peltikhin.rest.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService UserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(UserDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        System.setProperty("https.protocols", "TLSv1.2");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http
                .httpBasic().and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .and()
                .exceptionHandling()
                .and()
                .csrf().disable();
        http.cors().disable();
    }
}
