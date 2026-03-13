package bswe.gamifiedevidencebasednursing.auth.dto;

/**
 * Data Transfer Object for authentication requests.
 * Contains user credentials for registration and login.
 */
public class AuthRequest {
  private String username;
  private String password;

  /**
   * Default constructor.
   */
  public AuthRequest() {
  }

  /**
   * Constructs an authentication request with username and password.
   *
   * @param username the username
   * @param password the password
   */
  public AuthRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   *
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
