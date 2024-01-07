package com.akademi11.task.impl;


import com.akademi11.task.api.TaskDto;
import com.akademi11.task.api.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public List<TaskDto> findByUserId(Long id){
        return taskRepository.findAllByUserId(id)
                .stream()
                .map(this::toDto)
                .toList();
    }
    public TaskDto toDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.id = task.getId();
        taskDto.title = task.getTitle();
        taskDto.description = task.getDescription();
        taskDto.status = task.getStatus();
        return taskDto;
    }
}
