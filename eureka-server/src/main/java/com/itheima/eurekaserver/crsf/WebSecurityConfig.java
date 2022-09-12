package com.itheima.eurekaserver.crsf;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        super.configure(http);

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("templates/*").permitAll()
                .antMatchers("static/*").permitAll();
        //加这句是为了访问eureka控制台和/actuator时能做安全控制
        http.csrf().disable();
    }
}