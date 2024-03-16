package com.microservices.cards.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "error response",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(
            name="API path invoked by client"
    )
    private String apiPath;
    @Schema(
            name="Error code representing the error happened"
    )
    private HttpStatus errorCode;
    @Schema(
            name = "Error message representing the error happened"
    )
    private String errormessage;
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;
}
