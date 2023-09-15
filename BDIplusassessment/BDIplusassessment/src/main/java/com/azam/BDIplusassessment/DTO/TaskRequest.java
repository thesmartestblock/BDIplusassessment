package com.azam.BDIplusassessment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private String title;
    private String description;
    private String date;
    private String owner;
}

