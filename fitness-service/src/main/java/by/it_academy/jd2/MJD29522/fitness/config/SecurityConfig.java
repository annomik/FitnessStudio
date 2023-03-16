package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.web.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and();

      // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        http
                .authorizeHttpRequests( (authz) -> authz
                                .requestMatchers("/api/v1/users/registration").permitAll()
                                .requestMatchers("/api/v1/users/verification").permitAll()
                                .requestMatchers("/api/v1/users/login").permitAll()
                                .requestMatchers("/api/v1/users/me").authenticated()
                                .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/users").hasRole("ADMIN")
                                .requestMatchers("/api/v1/product").permitAll()
                                .requestMatchers("/api/v1/product/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/recipe").authenticated()
                                .requestMatchers("/api/v1/recipe/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/users/verification").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/users/registration").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users/me").authenticated()
//                        .requestMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/users/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/users/**").hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.GET,"/product").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/recipe").authenticated()
//                        .requestMatchers(HttpMethod.POST,"/recipe/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/recipe/**").hasRole("ADMIN")
                        //.requestMatchers("/api/v1/recipe/**").hasRole("ROLE_ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
              // .httpBasic(withDefaults());

        // Add JWT token filter
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}