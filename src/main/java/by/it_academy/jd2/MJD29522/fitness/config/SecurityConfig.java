package by.it_academy.jd2.MJD29522.fitness.config;

import by.it_academy.jd2.MJD29522.fitness.web.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
      //  extends WebSecurityConfigurerAdapter{

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
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/verification").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/registration").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/me").authenticated()
                        .requestMatchers("/api/v1/users/**").authenticated()
                        .requestMatchers("/api/v1/users/**").hasRole("ROLE_ADMIN")
                        .requestMatchers("/api/v1/product/**").authenticated()
                        .requestMatchers("/api/v1/recipe/**").authenticated()
                        .requestMatchers("/api/v1/users/**").hasRole("ROLE_ADMIN")

//                        .antMatchers("/api/v1/users/registration").permitAll()
//                        .antMatchers("/api/v1/users/verification").permitAll()
//                        .antMatchers("/api/v1/users/me").authenticated()
//                        .antMatchers("/api/v1/product/**").authenticated()
//                        .antMatchers("/api/v1/recipe/**").authenticated()
//                        .antMatchers("/api/v1/users/login").permitAll()
//                        .antMatchers("/api/v1/users/**").hasRole("ROLE_ADMIN")
                         .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        // Add JWT token filter
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}