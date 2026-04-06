package bswe.gamifiedevidencebasednursing.feature.retryroom.service;

import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RetryRoomService {

  Logger logger = LoggerFactory.getLogger(RetryRoomService.class);
  private final RoomRepository roomRepository;

  public RetryRoomService(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  public ResponseEntity<Integer> retryRoomOfKnowledge(long roomId) {
    logger.info("Trying to retry room of knowledge with id {}", roomId);
    return ResponseEntity.ok(retry(roomId));
  }

  public ResponseEntity<Integer> retryRoomOfAbstracts(long roomId) {
    logger.info("Trying to retry room of abstracts with id {}", roomId);
    return ResponseEntity.ok(retry(roomId));
  }


  private int retry(long roomId) {
    Optional<Room> room = roomRepository.findById(roomId);
    if (room.isPresent()) {
      room.get().setProgress(0);
      roomRepository.save(room.get());
      return room.get().getProgress();
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid question or room ID");
  }
}



