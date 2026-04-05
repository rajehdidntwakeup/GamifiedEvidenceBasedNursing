package bswe.gamifiedevidencebasednursing.security;

import bswe.gamifiedevidencebasednursing.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security configuration for the application.
 * Configures Spring Security with JWT authentication and role-based authorization.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserRepository userRepository;

  /**
   * Constructs a security configuration with the user repository.
   *
   * @param userRepository the admin repository for loading user details
   */
  public SecurityConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Configures the security filter chain with authentication and authorization rules.
   *
   * @param http the HTTP security configurer
   * @param jwtAuthFilter the JWT authentication filter
   * @return the configured security filter chain
   * @throws Exception if configuration fails
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/rooms/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/rooms/**").permitAll()
            .requestMatchers(HttpMethod.PUT, "/api/rooms/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/game/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/game/**").permitAll()
            .requestMatchers("/h2-console/**", "/error").permitAll()
            .requestMatchers("/auth/**", "/api/auth/**", "/v3/api-docs/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/admin/isThereAdmin").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .cors(Customizer.withDefaults())
        .headers(headers -> headers
            .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  /**
   * Configures the authentication entry point for unauthorized requests.
   *
   * @return the authentication entry point
   */
  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return (request, response, authException) -> {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    };
  }

  /**
   * Creates the JWT authentication filter bean.
   *
   * @param jwtService the JWT service
   * @param userDetailsService the user details service
   * @return the JWT authentication filter
   */
  @Bean
  public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
    return new JwtAuthFilter(jwtService, userDetailsService);
  }

  /**
   * Configures the authentication provider with user details and password encoder.
   *
   * @return the authentication provider
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  /**
   * Provides the authentication manager from Spring Security configuration.
   *
   * @param config the authentication configuration
   * @return the authentication manager
   * @throws Exception if configuration fails
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Provides the password encoder for hashing passwords.
   *
   * @return the BCrypt password encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Configures the user details service for loading user data.
   *
   * @return the user details service
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:5173"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}
