package com.azam.BDIplusassessment.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azam.BDIplusassessment.DTO.AddTaskRequest;
import com.azam.BDIplusassessment.DTO.TaskRequest;
import com.azam.BDIplusassessment.Exception.NoTaskFoundException;
import com.azam.BDIplusassessment.Exception.TaskNotFoundException;
import com.azam.BDIplusassessment.Models.Task;
import com.azam.BDIplusassessment.Repository.TaskRepo;

import jakarta.validation.Valid;

@Service
public class TaskServices {

    @Autowired
    private TaskRepo repo;

    public List<Task> getAll() throws NoTaskFoundException{
        List<Task> tasks = repo.findAll();
        
        if(tasks == null || tasks.size() == 0){
            throw new NoTaskFoundException("Please add task, No tasks found");
        }
        return tasks;
    }

    public Task getTaskById(Long id) throws TaskNotFoundException{
        Task task = repo.findById(id).orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %s, Not Found ",id)));
        return task;
    }

    public Task createTask(AddTaskRequest request) {
        Task task = new Task(0l, request);
        return repo.save(task);
    }

    public String delete(Long id) throws NoTaskFoundException {
        repo.findAll().stream().filter(x -> x.getId() == id).findAny().orElseThrow(()-> new NoTaskFoundException(String.format("Task with id %s, Not Found ",id)));
        repo.deleteById(id);
        return "success";
    }

    public Task updateTask(Long id, TaskRequest request) throws NoTaskFoundException{
        Task task = repo.findById(id).orElseThrow(()-> new NoTaskFoundException(String.format("Task with id %s, Not Found ",id)));

        if(request.getDate() != null)
            task.setDate(request.getDate());
        if(request.getTitle() != null)
            task.setTitle(request.getTitle());
        if(request.getOwner() != null)
            task.setOwner(request.getOwner());
        if(request.getDescription() != null)
            task.setDescription(request.getDescription());

        return repo.save(task);
    }

    public Task putTask(Long id, @Valid AddTaskRequest request) {
        Task task = new Task(id, request);
        return repo.save(task);
    }


}
