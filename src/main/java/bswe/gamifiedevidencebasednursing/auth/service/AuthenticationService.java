package bswe.gamifiedevidencebasednursing.auth.service;

import bswe.gamifiedevidencebasednursing.auth.dto.AuthRequest;
import bswe.gamifiedevidencebasednursing.auth.dto.AuthResponse;
import bswe.gamifiedevidencebasednursing.domain.User;
import bswe.gamifiedevidencebasednursing.repository.UserRepository;
import bswe.gamifiedevidencebasednursing.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for user authentication operations.
 * Handles user registration and login with JWT token generation.
 */
@Service
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  /**
   * Constructs an authentication service with required dependencies.
   *
   * @param userRepository the admin repository
   * @param passwordEncoder the password encoder
   * @param jwtService the JWT service
   * @param authenticationManager the authentication manager
   */
  public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }


  public AuthResponse register(AuthRequest request) {
    User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), "ROLE_ADMIN");
    userRepository.save(user);

    String token = jwtService.generateToken(user);
    return new AuthResponse(token, true);
  }

  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = userRepository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    boolean isAdmin = user.getRole().equals("ROLE_ADMIN");
    return new AuthResponse(jwtToken, isAdmin);
  }
}
