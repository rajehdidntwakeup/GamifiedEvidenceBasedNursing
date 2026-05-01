package bswe.gamifiedevidencebasednursing.feature.roomofanalytics.controller;

import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.dto.request.SubmissionDto;
import bswe.gamifiedevidencebasednursing.feature.roomofanalytics.service.RoomOfAnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms/roomofanalytics")
public class RoomOfAnalyticsController {

  private final RoomOfAnalyticsService roomOfAnalyticsService;

  public RoomOfAnalyticsController(RoomOfAnalyticsService roomOfAnalyticsService) {
    this.roomOfAnalyticsService = roomOfAnalyticsService;
  }

  @PostMapping(value = "/submit")
  public ResponseEntity<String> submitAnalytics(@RequestBody SubmissionDto submissionDto) {
    return roomOfAnalyticsService.submitAnalytics(submissionDto);
  }
}
