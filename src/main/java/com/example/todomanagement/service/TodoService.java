package com.example.todomanagement.service;

import com.example.todomanagement.dto.Tododto;

import java.util.List;


public interface TodoService {

    Tododto addTodo(Tododto tododto);

    Tododto getTodoById(Long id);

    List<Tododto> getAllTodos();

    Tododto updateTodo(Tododto tododto , Long id);

    void deleteTodoById(Long id);

    void deleteAllTodos();

    Tododto todoChangeStatues(Long id , boolean status);
}
