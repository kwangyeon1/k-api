package api.freelive.config.security;

import api.freelive.config.security.auth.JwtFilter;
import api.freelive.config.security.auth.JwtProvider;
import api.freelive.config.security.auth.RestApiAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.secret}") private String secretKey;
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return ((request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("ACCESS DENIED");
            response.getWriter().flush();
            response.getWriter().close();
        });
    }


    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);
    }

    @Bean
    public JwtFilter jwtFilter(JwtProvider jwtProvider) {
        return new JwtFilter(jwtProvider);
    }

    @Bean
    public RestApiAuthenticationEntryPoint restApiAuthenticationEntryPoint() {
        return new RestApiAuthenticationEntryPoint();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .cors().configurationSource(configurationSource())

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restApiAuthenticationEntryPoint())


                .and()
                .authorizeRequests()
                .antMatchers("/upload/**").permitAll()
                .antMatchers("/post/read/**").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/test-login").permitAll()
                .antMatchers("/user/join").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger*/**").permitAll()
                .anyRequest().authenticated()


                .and()
                .addFilterBefore(jwtFilter(jwtProvider()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
