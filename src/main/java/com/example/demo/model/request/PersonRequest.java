package com.example.demo.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class PersonRequest {

    @NonNull
    @Size(min = 3, max=20)
    @Schema(description = "Person first name", example = "Vardenis")
    private String firstName;

    @Schema(description = "Person last name", example = "Pavardenis")
    private String lastName;

    @Schema(description = "Person email", example = "email@gmail.com")
    private String email;

    @Schema(description = "Person phone", example = "+37061111111")
    private String phone;
}
