package com.example.todomanagement.controller;

import com.example.todomanagement.dto.Tododto;
import com.example.todomanagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todo")
public class TodoController {

    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add")
    public ResponseEntity<Tododto> addTodo(@RequestBody Tododto tododto){
        Tododto savedTodo = todoService.addTodo(tododto);
        return new ResponseEntity<>(savedTodo , HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("find/{id}")
    public ResponseEntity<Tododto> getTodoById(@PathVariable Long id){
        Tododto foundByIdTodo = todoService.getTodoById(id);
        return new ResponseEntity<>(foundByIdTodo,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public ResponseEntity<List<Tododto>> getAllTodos(){
        List<Tododto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<Tododto> updateTodo(@RequestBody Tododto tododto ,@PathVariable("id") Long todoId){
        Tododto updatedTodo =  todoService.updateTodo(tododto,todoId);

        return new ResponseEntity<>(updatedTodo,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public void deleteTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete")
    public void deleteTodoById(){
        todoService.deleteAllTodos();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<Tododto> changeTodoStatues(@PathVariable("id") Long id ,@RequestBody boolean completed){
        Tododto todoStatusChanged = todoService.todoChangeStatues(id,completed);
        return new ResponseEntity<>(todoStatusChanged,HttpStatus.OK);
    }
}
