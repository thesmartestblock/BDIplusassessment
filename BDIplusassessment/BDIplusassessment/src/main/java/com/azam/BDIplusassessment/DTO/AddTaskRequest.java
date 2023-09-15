package com.azam.BDIplusassessment.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {

    @NotNull(message = "please give a valid input for title")
    private String title;
    @NotNull(message = "please give a valid input for description")
    private String description;
    @NotNull(message = "Date cannot be empty")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",message = "please give a valid input for date")
    private String date;
    @NotNull(message = "please give a valid input for owner")
    private String owner;
}
