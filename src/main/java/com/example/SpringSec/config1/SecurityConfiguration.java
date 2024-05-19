package com.example.SpringSec.config1;


import com.example.SpringSec.config1.jwt.JwtAuthenticationEntryPoint;
import com.example.SpringSec.config1.jwt.JwtRequestFilter;
import com.example.SpringSec.entity.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .cors().and().csrf().disable().authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        new AntPathRequestMatcher("/api/auth/**"),
                                        new AntPathRequestMatcher("/h2-console/**"),
                                        new AntPathRequestMatcher("/api/users/**"),
                                        new AntPathRequestMatcher("/"),
                                        new AntPathRequestMatcher("/v3/api-docs/**"),
                                        new AntPathRequestMatcher("/swagger-ui/**"),
                                        new AntPathRequestMatcher("/swagger-ui.html"),
                                        new AntPathRequestMatcher("/api/contact"),
                                        new AntPathRequestMatcher("/api/contact/news-letter"),
                                        new AntPathRequestMatcher("/ws-api/**")
                                ).permitAll()
                                .requestMatchers(
                                        new AntPathRequestMatcher("/api/admin/**/*"),
                                        new AntPathRequestMatcher("/api/admin/*"),
                                        new AntPathRequestMatcher("/api/admin/**")
                                ).hasRole("ADMIN")
                                .requestMatchers(
                                        new AntPathRequestMatcher("/api/test/authtest")
                                ).hasAuthority("CREATE_PRODUCT")
                                .anyRequest().authenticated()

                )
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}