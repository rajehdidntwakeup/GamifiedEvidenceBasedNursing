package bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.service;

import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.AdminNotificationDto;
import bswe.gamifiedevidencebasednursing.feature.roomofsciencebattle.dto.response.ScienceBattleFeedbackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScienceBattleNotificationService {

  private final SimpMessagingTemplate messagingTemplate;
  public static final Logger logger = LoggerFactory.getLogger(ScienceBattleNotificationService.class);

  public ScienceBattleNotificationService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void notifyAdmin(AdminNotificationDto notification) {
    messagingTemplate.convertAndSend("/topic/sciencebattle/submissions", notification);
  }

  public void notifyTeam(ScienceBattleFeedbackDto feedback) {
    logger.info("Sending feedback notification for mission: {}", feedback.missionName());
    messagingTemplate.convertAndSend("/topic/sciencebattle/feedback/" + feedback.missionName() + "/feedback", feedback);
  }
}
