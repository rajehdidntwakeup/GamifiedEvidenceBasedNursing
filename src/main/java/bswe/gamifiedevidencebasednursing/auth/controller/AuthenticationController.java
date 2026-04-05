package bswe.gamifiedevidencebasednursing.auth.controller;


import bswe.gamifiedevidencebasednursing.auth.dto.AuthRequest;
import bswe.gamifiedevidencebasednursing.auth.dto.AuthResponse;
import bswe.gamifiedevidencebasednursing.auth.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for user authentication operations.
 * Handles user registration and login requests.
 */
@RestController
@RequestMapping({"/auth", "/api/auth"})
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Constructs an authentication controller with the required service.
   *
   * @param authenticationService the authentication service
   */
  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  /**
   * Registers a new user account.
   *
   * @param request the authentication request containing username and password
   * @return response entity containing JWT token and user role information
   */
  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(
      @RequestBody AuthRequest request
  ) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  /**
   * Authenticates an existing user.
   *
   * @param request the authentication request containing username and password
   * @return response entity containing JWT token and user role information
   */
  @PostMapping({"/login", "/authenticate"})
  public ResponseEntity<AuthResponse> authenticate(
      @RequestBody AuthRequest request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}

