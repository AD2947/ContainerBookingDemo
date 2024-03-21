package com.example.demo.ui.dto.request;

//import com.example.demo.utils.OneOf;
import com.example.demo.utils.OneOf;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    @NotNull
    //@Ignore can be used
    //@Pattern(regexp = "(20)|(40)", message = "Container size is not matching, expected 20 or 40")
    @OneOf({20,40})
    @JsonProperty("containerSize")
    private int containerSize;

    @NotNull
    @Pattern(regexp = "(REEFER)|(DRY)", message = "Container type is not matching, expected DRY or REEFER")
    @JsonProperty("containerType")
    //@Value()
    private String containerType;

    @NotNull
    @JsonProperty("origin")
    @Size(min=5, message = "{validation.origin.short}")
    @Size(max=20, message = "{validation.origin.long}")
    private String origin;

    @NotNull
    @JsonProperty("destination")
    @Size(min=5, message = "{validation.destination.short}")
    @Size(max=20, message = "{validation.destination.long}")
    private String destination;

    @NotNull
    @JsonProperty("quantity")
    @Min(value = 1, message = "validation.quantity.minimum")
    @Max(value = 100, message = "validation.quantity.maximum")
    private Integer quantity;

    @NotNull
    @JsonProperty("timestamp")
    //@Pattern(regexp = "[]")
    @DateTimeFormat(pattern = "yyyy-mm-ddThh:mm:ssZ")
    private String timestamp;

}
