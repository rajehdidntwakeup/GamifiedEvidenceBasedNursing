package bswe.gamifiedevidencebasednursing.service;

import bswe.gamifiedevidencebasednursing.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  public UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

}
