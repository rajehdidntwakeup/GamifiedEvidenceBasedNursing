package bswe.gamifiedevidencebasednursing.websocket.interceptor;

import bswe.gamifiedevidencebasednursing.security.JwtService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * WebSocket authentication interceptor for JWT token validation.
 * Validates JWT tokens in STOMP CONNECT headers.
 */
public class WebSocketAuthInterceptor implements ChannelInterceptor {

  private final JwtService jwtService;

  public WebSocketAuthInterceptor(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

    if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
      List<String> authorization = accessor.getNativeHeader("Authorization");

      if (authorization != null && !authorization.isEmpty()) {
        String token = authorization.get(0);
        if (token.startsWith("Bearer ")) {
          token = token.substring(7);
        }

        String username = jwtService.extractUsername(token);
        if (username != null && isTokenValid(token)) {
          // Create authentication token
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(username, null, List.of());
          SecurityContextHolder.getContext().setAuthentication(authentication);
          accessor.setUser(authentication);
        }
      }
    }

    return message;
  }

  private boolean isTokenValid(String token) {
    try {
      // Simple validation - extract username and check not expired
      String extractedUsername = jwtService.extractUsername(token);
      return extractedUsername != null && !extractedUsername.isEmpty();
    } catch (Exception e) {
      return false;
    }
  }
}
