package com.github.pavelvil.web.rest;

import com.github.pavelvil.model.Todo;
import com.github.pavelvil.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/todos")
    public List<Todo> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/api/todo/{id}")
    public ResponseEntity<Todo> findById(@PathVariable("id") Long id) {
        Optional<Todo> todo = service.findById(id);
        return todo.map(response -> ResponseEntity.ok().body(response)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/api/todos/{label}")
    public ResponseEntity<List<Todo>> findByLabel(@PathVariable("label") String label) {
        List<Todo> todos = service.findByLabel(label);
        return ResponseEntity.ok().body(todos);
    }

    @PostMapping(value = "/api/todo")
    public ResponseEntity<Todo> save(@RequestBody Todo todo) throws URISyntaxException {
        Todo newTodo = service.save(todo);
        return ResponseEntity.created(new URI("/api/todo/" + newTodo.getId())).body(newTodo);
    }

    @PutMapping(value = "/api/todo/{id}")
    public ResponseEntity<Todo> update(@PathVariable("id") Long id, @RequestBody Todo todo) {
        todo.setId(id);
        service.update(todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping(value = "/api/todo/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
