package com.akademi11.task.api;

import java.util.List;

public interface TaskService {
    List<TaskDto> findByUserId(Long id);
}
