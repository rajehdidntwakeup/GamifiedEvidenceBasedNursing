package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST controller for admin dashboard operations.
 * Handles game management, team monitoring, and analytics.
 */
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  /**
   * Check if there are any games (administrators).
   *
   * @return true if at least one game exists
   */
  @GetMapping("/isThereAdmin")
  @PreAuthorize("permitAll()")
  public ResponseEntity<Boolean> isThereAdmin() {
    boolean isThereAdmin = adminService.isThereAdmin();
    return ResponseEntity.ok(isThereAdmin);
  }
}
