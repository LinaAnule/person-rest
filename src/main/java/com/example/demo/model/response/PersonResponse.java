package com.example.demo.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonResponse {
    @Schema(description = "person name", example = "Vardenis")
    private String firstName;
    @Schema(description = "person last name", example = "Pavardenis")
    private String lastName;
    @Schema(description = "email", example = "a@mial.com")
    private String email;
    @Schema(description = "phone", example = "+37061111111")
    private String phone;
}
