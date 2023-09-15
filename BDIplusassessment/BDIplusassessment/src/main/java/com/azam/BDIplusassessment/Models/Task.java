package com.azam.BDIplusassessment.Models;

import com.azam.BDIplusassessment.DTO.AddTaskRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Task_Table")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String date;
    private String owner;

    public Task(Long i, AddTaskRequest request) {
        this.id = i;
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.date = request.getDate();
        this.owner = request.getOwner();
    }
    
}
