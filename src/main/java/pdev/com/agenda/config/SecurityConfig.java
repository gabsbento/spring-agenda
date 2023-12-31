package pdev.com.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                (authorize) -> {
                    authorize.requestMatchers("/public").permitAll();
                    authorize.requestMatchers("/logout").permitAll();
                    authorize.requestMatchers("/usuario").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
