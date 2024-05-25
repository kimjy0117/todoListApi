package com.example.demo.service;

import com.example.demo.domain.Todo;
import com.example.demo.domain.Member;
import com.example.demo.dto.TodoDto;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public TodoService(TodoRepository todoRepository, MemberRepository userRepository) {
        this.todoRepository = todoRepository;
        this.memberRepository = userRepository;
    }

    //read
    public List<TodoDto> getTodosByUserId(int memberId){
        List<Todo> todos = todoRepository.findByMemberId(memberId);
        return todos.stream()
                .map(TodoDto::from)
                .collect(Collectors.toList());
    }

    //create
    public void createTodo(TodoDto todoDto) {
        Member member = memberRepository.findById(todoDto.getUserId()).orElse(null);
        if (member == null)
            return;
        Todo todo = new Todo();
        todo.setMember(member);
        todo.setContent(todoDto.getContent());
        todoRepository.save(todo);
    }

    //update
    public void updateTodo(int id, TodoDto todoDto){
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null){
            todo.setContent(todoDto.getContent());
            todo.setCreated_at(todoDto.getCreated_at());
            todoRepository.save(todo);
        }
    }

    //delete
    public void deleteTodo(int id){
        todoRepository.deleteById(id);
    }
}
