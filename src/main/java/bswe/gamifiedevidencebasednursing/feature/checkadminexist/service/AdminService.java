package bswe.gamifiedevidencebasednursing.feature.checkadminexist.service;

import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.User;
import bswe.gamifiedevidencebasednursing.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  private final UserRepository userRepository;

  public AdminService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public boolean isThereAdmin() {
    List<User> users = userRepository.findAll();
    return !users.isEmpty();
  }

}
