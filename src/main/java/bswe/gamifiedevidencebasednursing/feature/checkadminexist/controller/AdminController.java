package bswe.gamifiedevidencebasednursing.feature.checkadminexist.controller;

import bswe.gamifiedevidencebasednursing.feature.checkadminexist.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @GetMapping("/isThereAdmin")
  @PreAuthorize("permitAll()")
  public ResponseEntity<Boolean> isThereAdmin() {
    boolean isThereAdmin = adminService.isThereAdmin();
    return ResponseEntity.ok(isThereAdmin);
  }
}
