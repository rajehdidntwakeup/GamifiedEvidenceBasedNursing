package bswe.gamifiedevidencebasednursing.controller;

import bswe.gamifiedevidencebasednursing.domain.dto.request.CreateGameRequest;
import bswe.gamifiedevidencebasednursing.domain.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public ResponseEntity<GameResponseDto> createGame(@RequestBody CreateGameRequest request) {
    GameResponseDto gameResponseDto = gameService.createGame(request.password());
    if (gameResponseDto != null) {
      return ResponseEntity.ok(gameResponseDto);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
