package com.azam.BDIplusassessment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azam.BDIplusassessment.Models.Task;

public interface TaskRepo extends JpaRepository<Task,Long>{
    
}
