package bswe.gamifiedevidencebasednursing.feature.proceedtothenextroom.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProceedDto(@NotBlank long roomId) {
}
