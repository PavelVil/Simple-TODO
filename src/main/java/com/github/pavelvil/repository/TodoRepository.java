package com.github.pavelvil.repository;


import com.github.pavelvil.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findAll();

}
