package bswe.gamifiedevidencebasednursing.domain.dto;

import java.util.List;

/**
 * DTO for question data.
 */
public record QuestionDto(
    Long id,
    String title,
    String image,
    List<AnswerDto> answers
) {
  /**
   * DTO for answer options (without revealing correctness).
   */
  public record AnswerDto(
      Long id,
      String text
  ) {
  }
}
