package bswe.gamifiedevidencebasednursing.feature.roomtime.service;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import bswe.gamifiedevidencebasednursing.domain.Room;
import bswe.gamifiedevidencebasednursing.feature.roomtime.dto.RoomTimeResponse;
import bswe.gamifiedevidencebasednursing.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomTimeService {

  private final RoomRepository roomRepository;
  private final Clock clock;

  public RoomTimeService(RoomRepository roomRepository, Clock clock) {
    this.roomRepository = roomRepository;
    this.clock = clock;
  }


  public RoomTimeResponse howmuchtimedowehave(long roomId) {
    Instant now = Instant.now(clock);
    Optional<Room> room = roomRepository.findById(roomId);
    if (room.isPresent()) {
      Instant start = room.get().getStartTime();
      int extraTime = room.get().getExtraTime();
      int originalTime = room.get().getLocation().getTimer();

      Duration totalDuration = Duration.ofMinutes(originalTime + extraTime);
      Instant deadline = start.plus(totalDuration);
      Duration remaining = Duration.between(now, deadline);

      if (remaining.isNegative()) {
        return new RoomTimeResponse(0, 0);
      } else {
        return new RoomTimeResponse(remaining.toMinutes(), remaining.toSecondsPart());
      }
    }
    return new RoomTimeResponse(0, 0);

  }
}
