package com.ndabhi.demo;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
            .userDnPatterns("uid={0},ou=people")
            .groupSearchBase("ou=groups")
            .groupRoleAttribute("cn")
            .groupSearchFilter("uniqueMember={0}")
            .contextSource()
            .url("ldap://localhost:8399/dc=springframework,dc=org")
            .and()
            .passwordCompare()
            .passwordAttribute("userPassword");

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/get1").hasRole("CIRCUS_ADMIN")
                .antMatchers("/submit1").hasAnyRole("CIRCUS_CHOREOGRAPH", "CIRCUS_ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .cors()
                .and().csrf().disable()
                .formLogin();
    }
}
