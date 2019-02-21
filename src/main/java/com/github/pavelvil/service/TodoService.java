package com.github.pavelvil.service;


import com.github.pavelvil.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    Long count();

    List<Todo> getAll();

    Optional<Todo> findById(Long id);

    List<Todo> findByLabel(String label);

    Todo save(Todo todo);

    Todo update(Todo todo);

    void deleteById(Long id);

}
