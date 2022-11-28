package com.bosonit.training.security.security;


import com.bosonit.training.security.filter.CustomAuthenticationFilter;
import com.bosonit.training.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(authenticationConfiguration));
        customAuthenticationFilter.setFilterProcessesUrl("/login");



        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(GET,"/getall/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers(POST,"/addPerson/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST,"/addRole/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

        public static void main(String[] args){
        System.out.println("pass "+new BCryptPasswordEncoder().encode("142536"));
    }




}
