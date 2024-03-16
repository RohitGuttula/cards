package com.microservices.cards.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDTO {
    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;
    @Schema(
            description = "status message in response"
    )
    private String statusMessage;
}
