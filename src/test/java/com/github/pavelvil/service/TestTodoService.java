package com.github.pavelvil.service;

import com.github.pavelvil.AbstractTest;
import com.github.pavelvil.model.Todo;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestTodoService extends AbstractTest {

    @Test
    public void testCountInDB() throws Exception {
        Long oldCount = todoService.count();
        todoService.save(new Todo(NEW_LABEL, true, false));
        Long newCount = todoService.count();
        assertTrue(oldCount < newCount);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Todo> todos = todoService.getAll();
        assertTrue(todos.size() > 0);
    }

    @Test
    public void testFindById() throws Exception {
        Optional<Todo> todoOptional = todoService.findById(ID);
        assertTrue(todoOptional.isPresent());
        assertTrue(todoOptional.get().getId().equals(ID));
    }

    @Test
    public void testFindByLabel() throws Exception {
        List<Todo> todos = todoService.findByLabel(LABEL);
        assertTrue(todos.size() == 1);
        assertEquals(LABEL, todos.get(0).getLabel());
    }

    @Test
    public void testSave() throws Exception {
        Long oldCount = todoService.count();
        Todo todo = new Todo(NEW_LABEL, true, false);
        todo = todoService.save(todo);
        Long newCount = todoService.count();
        assertTrue(oldCount < newCount);
        assertNotNull(todo.getId());
        assertEquals(NEW_LABEL, todo.getLabel());
    }

    @Test
    public void testUpdate() throws Exception {
        Optional<Todo> todoOptional = todoService.findById(ID);
        assertTrue(todoOptional.isPresent());
        Todo todo = todoOptional.get();
        String oldLabel = todo.getLabel();
        todo.setLabel(NEW_LABEL);
        todo = todoService.update(todo);
        assertNotEquals(oldLabel, todo.getLabel());
    }

    @Test
    public void testDeleteById() throws Exception {
        Optional<Todo> todoOptional = todoService.findById(ID);
        assertTrue(todoOptional.isPresent());
        todoService.deleteById(ID);
        todoOptional = todoService.findById(ID);
        assertFalse(todoOptional.isPresent());
    }
}
