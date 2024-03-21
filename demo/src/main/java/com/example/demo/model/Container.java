package com.example.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "containers")
public class Container {
    @Id
    private int id;

    @NotNull
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


    //Getters and setters

    @Override
    public String toString() {
        return "";
    }
}
