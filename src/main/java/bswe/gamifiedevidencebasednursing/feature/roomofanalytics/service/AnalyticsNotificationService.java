package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service;

import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AnalyticsFeedbackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsNotificationService {

  private final SimpMessagingTemplate messagingTemplate;
  public static final Logger logger = LoggerFactory.getLogger(AnalyticsNotificationService.class);

  public AnalyticsNotificationService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void notifyAdmin(AdminNotificationDto notification) {
    messagingTemplate.convertAndSend("/topic/analytics/submissions", notification);
  }

  public void notifyTeam(AnalyticsFeedbackDto feedback) {
    logger.info("Sending feedback notification for mission: {}", feedback.missionName());
    messagingTemplate.convertAndSend("/topic/mission/analytics/" + feedback.missionName() + "/feedback", feedback);
  }
}
