package com.example.demo.dto;

import com.example.demo.domain.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoDto {
    private int id;
    private int memberId;
    private String content;
    private Boolean completed;
    private LocalDateTime created_at;

    public TodoDto() {
        this.completed = false;
    }

    public static TodoDto from(Todo todo){
        TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setCreated_at(todo.getCreated_at());
        dto.setContent(todo.getContent());
        dto.setMemberId(todo.getMember().getId());
        dto.setCompleted(todo.getCompleted());
        return dto;
    }
}
