package bswe.gamifiedevidencebasednursing.gamecreation.controller;


import bswe.gamifiedevidencebasednursing.domain.dto.request.CreateGameRequest;
import bswe.gamifiedevidencebasednursing.domain.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.gamecreation.service.GameCreationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameCreationController {

  private final GameCreationService gameCreationService;

  public GameCreationController(GameCreationService gameCreationService) {
    this.gameCreationService = gameCreationService;
  }

  @PostMapping("/create")
  public ResponseEntity<GameResponseDto> createGame(@RequestBody CreateGameRequest request) {
    GameResponseDto gameResponseDto = gameCreationService.createGame(request.password());
    if (gameResponseDto != null) {
      return ResponseEntity.ok(gameResponseDto);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

}
