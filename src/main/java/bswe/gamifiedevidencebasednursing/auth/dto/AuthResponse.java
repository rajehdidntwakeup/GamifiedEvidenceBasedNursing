package bswe.gamifiedevidencebasednursing.auth.dto;

/**
 * Data Transfer Object for authentication responses.
 * Contains JWT token and user role information.
 */
public class AuthResponse {
  private String token;
  private boolean admin;

  /**
   * Default constructor.
   */
  public AuthResponse() {
  }

  /**
   * Constructs an authentication response with token and admin status.
   *
   * @param token the JWT authentication token
   * @param admin whether the user has admin (WRITE) privileges
   */
  public AuthResponse(String token, boolean admin) {
    this.token = token;
    this.admin = admin;
  }

  /**
   * Gets the JWT token.
   *
   * @return the JWT token
   */
  public String getToken() {
    return token;
  }

  /**
   * Sets the JWT token.
   *
   * @param token the JWT token to set
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Checks if the user has admin privileges.
   *
   * @return true if the user is an admin, false otherwise
   */
  public boolean isAdmin() {
    return admin;
  }

  /**
   * Sets the admin status.
   *
   * @param admin the admin status to set
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
