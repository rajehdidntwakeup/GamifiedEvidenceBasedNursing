package bswe.gamifiedevidencebasednursing.feature.admindashboard.controller;

import bswe.gamifiedevidencebasednursing.feature.admindashboard.dto.response.SessionPasswordsDto;
import bswe.gamifiedevidencebasednursing.feature.admindashboard.service.AdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

  private final AdminDashboardService adminDashboardService;

  public AdminDashboardController(AdminDashboardService adminDashboardService) {
    this.adminDashboardService = adminDashboardService;
  }

  @GetMapping("/missions-passwords")
  public ResponseEntity<SessionPasswordsDto> getMissionsPasswords() {
    return adminDashboardService.getSessionPasswords();
  }

  @PostMapping("/close-game-session")
  public ResponseEntity<Void> closeGameSession() {
   return adminDashboardService.closeGameSession();
  }
}
