package com.example.demo.ui.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailablilityResponse {

    @JsonProperty("available")
    @NonNull
    private Boolean available;
}
