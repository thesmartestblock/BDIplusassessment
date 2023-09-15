package com.azam.BDIplusassessment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azam.BDIplusassessment.DTO.AddTaskRequest;
import com.azam.BDIplusassessment.DTO.TaskRequest;
import com.azam.BDIplusassessment.Exception.NoTaskFoundException;
import com.azam.BDIplusassessment.Exception.TaskNotFoundException;
import com.azam.BDIplusassessment.Models.Task;
import com.azam.BDIplusassessment.Services.TaskServices;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/v1")
public class TaskController {

    @Autowired
    private TaskServices service;
    
    @Tag(name = "Home", description = "Starting Page")
    @GetMapping(value="/")
    public String getMethodName() {
        return "home";
    }

    @Tag(name = "All task", description = "All task present in Database will be displayed")
    @GetMapping(value="/alltask")
    public ResponseEntity<List<Task>> getAlltask() throws NoTaskFoundException {

        return ResponseEntity.ok().body(service.getAll());
    }

    @Tag(name = "Find Task", description = "You can find single task by id")
    @GetMapping(value="/task/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) throws TaskNotFoundException {
        return ResponseEntity.ok().body(service.getTaskById(id));
    }
    
    @Tag(name = "Add Task", description = "you can add a task")
    @PostMapping(value="/task")
    public ResponseEntity<Task> postTask(@RequestBody @Valid AddTaskRequest request) {
        
        return new ResponseEntity<Task>(service.createTask(request), HttpStatus.CREATED);
    }

    @Tag(name = "Update whole Task", description = "you can update whole task")
    @PutMapping(value="/task/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id ,@RequestBody @Valid AddTaskRequest request) {
        
        return new ResponseEntity<Task>(service.putTask(id ,request), HttpStatus.CREATED);
    }

    @Tag(name = "Update task", description = "You can update the fields in the existing task")
    @PatchMapping("/task/{id}")
    public ResponseEntity<Task> patchTask(@PathVariable Long id , @RequestBody TaskRequest request) throws NoTaskFoundException {
        
        return ResponseEntity.ok().body(service.updateTask(id,request));
    }
    
    @Tag(name = "Delete task", description = "You can Delete the  task")
    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) throws NoTaskFoundException {
        
        return new ResponseEntity<String>(service.delete(id), HttpStatus.NO_CONTENT);
    }
    
    
}
