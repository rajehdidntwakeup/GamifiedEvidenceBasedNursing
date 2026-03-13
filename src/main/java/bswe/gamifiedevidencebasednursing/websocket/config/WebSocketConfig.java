package bswe.gamifiedevidencebasednursing.websocket.config;

import bswe.gamifiedevidencebasednursing.security.JwtService;
import bswe.gamifiedevidencebasednursing.websocket.interceptor.WebSocketAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket configuration for real-time communication.
 * Configures STOMP protocol with SockJS fallback for team collaboration.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final JwtService jwtService;

  public WebSocketConfig(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // Enable a simple in-memory broker for team updates
    config.enableSimpleBroker("/topic", "/queue");
    // Client messages prefixed with /app go to @MessageMapping methods
    config.setApplicationDestinationPrefixes("/app");
    // User-specific messages
    config.setUserDestinationPrefix("/user");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // WebSocket endpoint with SockJS fallback
    registry.addEndpoint("/ws")
        .setAllowedOriginPatterns("*")
        .withSockJS();
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    // Add JWT authentication interceptor
    registration.interceptors(new WebSocketAuthInterceptor(jwtService));
  }
}
