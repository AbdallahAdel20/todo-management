package com.example.todomanagement.service.impl;

import com.example.todomanagement.dto.Tododto;
import com.example.todomanagement.entity.Todo;
import com.example.todomanagement.exception.ResourceNotFoundException;
import com.example.todomanagement.repo.TodoRepository;
import com.example.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;



    @Override
    public Tododto addTodo(Tododto tododto) {

        //    convert Tododto into todo jpa entity
//        Todo todo = new Todo();
//        todo.setTitle(tododto.getTitle());
//        todo.setDescription(tododto.getDescription());
//        todo.setCompleted(tododto.isCompleted());

        Todo todo = modelMapper.map(tododto , Todo.class);

//        Todo jpa entity

        Todo savedTodo = todoRepository.save(todo);

//        Convert todo jpa entity into dto tododto object

//        Tododto savedTodoDto = new Tododto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());
        Tododto savedTodoDto = modelMapper.map(savedTodo , Tododto.class);

        return savedTodoDto;
    }

    @Override
    public Tododto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Todo which id is "+id+" not found!"));

        return modelMapper.map(todo , Tododto.class);

    }

    @Override
    public List<Tododto> getAllTodos() {
        List<Todo> todos =  todoRepository.findAll();


        return todos.stream().map((todo)->modelMapper.map(todo,Tododto.class)).collect(Collectors.toList());
    }

    @Override
    public Tododto updateTodo(Tododto tododto, Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Todo which id is "+id+" not found!"));

        todo.setTitle(tododto.getTitle());
        todo.setDescription(tododto.getDescription());
        todo.setCompleted(tododto.isCompleted());




        return modelMapper.map(todoRepository.save(todo) , Tododto.class);
    }

    @Override
    public void deleteTodoById(Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Todo which id is "+id+" not found!"));
        todoRepository.delete(todo);
    }

    @Override
    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    @Override
    public Tododto todoChangeStatues(Long id, boolean status) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Todo which id is "+id+" not found!"));

        todo.setCompleted(status);
        return modelMapper.map(todoRepository.save(todo) , Tododto.class);
    }


}
