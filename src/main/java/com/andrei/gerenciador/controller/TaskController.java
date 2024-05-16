package com.andrei.gerenciador.controller;

import com.andrei.gerenciador.dto.TaskDto;
import com.andrei.gerenciador.exception.ResourceNotFoundException;
import com.andrei.gerenciador.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

        @Autowired
        private TaskService taskService;

        @GetMapping
        public List<TaskDto> getAllTasks() {
            return taskService.findAll();
        }

        @PostMapping
        public TaskDto createTask(@RequestBody TaskDto taskDto) {
            return taskService.save(taskDto);
        }

        @PutMapping("/{id}")
        public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDetails) {
            return taskService.update(id, taskDetails)
                    .orElseThrow(() -> new ResourceNotFoundException("Não existe essa task"));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteTask(@PathVariable Long id) {
            taskService.deleteById(id);
            return ResponseEntity.ok().build();
        }
}
