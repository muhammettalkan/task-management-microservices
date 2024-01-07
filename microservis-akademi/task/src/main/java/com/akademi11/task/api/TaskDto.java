package com.akademi11.task.api;


import com.akademi11.library.enums.Status;

public class TaskDto {
    public Long id;
    public String title;
    public String description;
    public Status status;
    public Long userId;

    public TaskDto() {
    }
}
