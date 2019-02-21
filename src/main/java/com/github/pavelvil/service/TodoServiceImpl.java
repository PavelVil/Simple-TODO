package com.github.pavelvil.service;

import com.github.pavelvil.model.Todo;
import com.github.pavelvil.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository repository;

    public TodoServiceImpl(@Autowired TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Todo> findByLabel(String label) {
        return getAll()
                .stream()
                .filter(todo -> todo.getLabel().toLowerCase().contains(label.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    @Override
    public Todo update(Todo todo) {
        return repository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
