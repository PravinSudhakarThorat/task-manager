package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @GetMapping
  public ResponseEntity<List<Task>> list() {
    return ResponseEntity.ok(taskRepository.findAll());
  }

  @PostMapping
  public ResponseEntity<Task> create(@RequestBody Task t) {
    Task saved = taskRepository.save(t);
    return ResponseEntity.status(201).body(saved);
  }
}
