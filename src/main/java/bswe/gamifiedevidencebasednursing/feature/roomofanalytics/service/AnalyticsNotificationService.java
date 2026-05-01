package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service;

import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.response.AnalyticsFeedbackDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsNotificationService {

  private final SimpMessagingTemplate messagingTemplate;

  public AnalyticsNotificationService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void notifyAdmin(AdminNotificationDto notification) {
    messagingTemplate.convertAndSend("/topic/analytics/submissions", notification);
  }

  public void notifyTeam(AnalyticsFeedbackDto feedback) {
    messagingTemplate.convertAndSend("/topic/mission/analytics/" + feedback.missionName() + "/feedback", feedback);
  }
}
