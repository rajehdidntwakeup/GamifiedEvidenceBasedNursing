package bswe.gamifiedevidencebasednursing.service;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.User;
import bswe.gamifiedevidencebasednursing.repository.GameRepository;
import bswe.gamifiedevidencebasednursing.repository.TeamRepository;
import bswe.gamifiedevidencebasednursing.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Service for admin dashboard operations.
 * Handles game management, team monitoring, and analytics.
 */
@Service
public class AdminService {

  private final GameRepository gameRepository;
  private final TeamRepository teamRepository;
  private final UserRepository userRepository;

  public AdminService(GameRepository gameRepository, TeamRepository teamRepository, UserRepository userRepository) {
    this.gameRepository = gameRepository;
    this.teamRepository = teamRepository;
    this.userRepository = userRepository;
  }


  public boolean isThereAdmin() {
    List<User> users = userRepository.findAll();
    return !users.isEmpty();
  }

}
