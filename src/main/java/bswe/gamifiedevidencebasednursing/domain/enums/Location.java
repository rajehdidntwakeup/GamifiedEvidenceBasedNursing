package bswe.gamifiedevidencebasednursing.domain.enums;

public enum Location {
  START("Start", null),
  ROOM_OF_KNOWLEDGE("Room of Knowledge", "E-C"),
  ROOM_OF_ABSTRACTS("Room of Abstracts", "F-O"),
  ROOM_OF_ANALYTICS("Room of Analytics", "L-R"),
  ROOM_OF_SCIENCE_BATTLE("Room of Science Battle", "N-E"),
  LAST_QUIZ("Last Quiz", null),
  END_STATE("End State", null);

  public final String name;
  public final String key;

  Location(String name, String key) {
    this.name = name;
    this.key = key;
  }
}
