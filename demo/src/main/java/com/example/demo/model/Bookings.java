package com.example.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Document(collection = "bookings")
public class Bookings {

    @Transient
    public static final String SEQUENCE_NAME = "bookingsSequence";

    @Id
    private Long bookingRef;

    @NotNull
    //@Ignore can be used

    private Integer containerSize;

    @NotNull
    private ContainerType containerType;

    @NotNull
    @Size(min=5, message = "{validation.origin.short}")
    @Size(max=20, message = "{validation.origin.long}")
    private String origin;

    @NotNull
    @Size(min=5, message = "{validation.destination.short}")
    @Size(max=20, message = "{validation.destination.long}")
    private String destination;

    @NotNull
    @Min(value = 1, message = "validation.quantity.minimum")
    @Max(value = 100, message = "validation.quantity.maximum")
    private Integer quantity;

    @NotNull
    //@Pattern(regexp = "[]")
    @DateTimeFormat(pattern = "yyyy-mm-ddThh:mm:ssZ")
    private String timestamp;

    public Bookings(int containerSize, ContainerType containerType, String origin, String destination, int quantity, String timestamp) {
        this.containerSize = containerSize;
        this.containerType = containerType;
        this.origin = origin;
        this.destination = destination;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }
}
