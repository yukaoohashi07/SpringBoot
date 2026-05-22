package sample.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(a -> a.anyRequest().permitAll())
            .formLogin(f -> f.disable())
            .httpBasic(h -> h.disable())
            .logout(l -> l.disable())
            ; 
        return http.build();
    }
}