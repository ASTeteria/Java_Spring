package javaspring.springhw.dto;

import lombok.Builder;



import java.time.OffsetDateTime;

@Builder
public record ErrorDto(
        String message,
        OffsetDateTime time
) {}