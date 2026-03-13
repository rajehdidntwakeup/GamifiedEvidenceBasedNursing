package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.domain.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }


  @PostMapping("/create")
  public ResponseEntity<GameResponseDto> createGame(@PathVariable String password) {
    GameResponseDto gameResponseDto = gameService.createGame(password);
    if (gameResponseDto != null) {
      return ResponseEntity.ok(gameResponseDto);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
