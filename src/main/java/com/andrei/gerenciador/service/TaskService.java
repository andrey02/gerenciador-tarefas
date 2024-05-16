package com.andrei.gerenciador.service;

import com.andrei.gerenciador.dto.TaskDto;
import com.andrei.gerenciador.model.Task;
import com.andrei.gerenciador.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDto save(TaskDto taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Optional<TaskDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDto);
    }

    public Optional<TaskDto> update(Long id, TaskDto taskDetails) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(taskDetails.getTitle());
                    existingTask.setDescription(taskDetails.getDescription());
                    existingTask.setStatus(taskDetails.getStatus());
                    Task updatedTask = taskRepository.save(existingTask);
                    return convertToDto(updatedTask);
                });
    }

    // Converter TaskDTO para Task
    private Task convertToEntity(TaskDto taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCreationDate(taskDTO.getCreationDate());
        task.setStatus(taskDTO.getStatus());
        return task;
    }

    // Converter Task para TaskDTO
    private TaskDto convertToDto(Task task) {
        TaskDto taskDTO = new TaskDto();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCreationDate(task.getCreationDate());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }
}
