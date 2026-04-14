package bswe.gamifiedevidencebasednursing.feature.gamecreation.controller;


import bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.request.CreateGameRequest;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.dto.response.GameResponseDto;
import bswe.gamifiedevidencebasednursing.feature.gamecreation.service.GameCreationService;
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
    return ResponseEntity.ok(gameResponseDto);
  }

}
