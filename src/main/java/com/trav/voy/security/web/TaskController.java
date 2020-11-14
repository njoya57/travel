/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.web;

import com.trav.voy.security.dao.TaskRepository;
import com.trav.voy.security.entities.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MEDION
 */
@RestController
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @GetMapping("/tasks")
    public List<Task>listTask(){
        return taskRepository.findAll();
    }
    
    @PostMapping("/tasks")
    public Task save(@RequestBody Task task){
        return taskRepository.save(task);
    }
}
