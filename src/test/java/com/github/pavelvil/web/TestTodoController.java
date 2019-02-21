package com.github.pavelvil.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pavelvil.AbstractTest;
import com.github.pavelvil.model.Todo;
import com.github.pavelvil.web.rest.TodoController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
public class TestTodoController extends AbstractTest {

    private final static String MANY_TODOS_URI = "/api/todos";
    private final static String TODO_URI = "/api/todo";

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void setup() {
        super.setup();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new TodoController(todoService))
                .build();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(MANY_TODOS_URI))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        String byIdUri = TODO_URI + "/" + ID;
        mockMvc.perform(get(byIdUri))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByLabel() throws Exception {
        String byLabelUri = MANY_TODOS_URI + "/" + LABEL;
        mockMvc.perform(get(byLabelUri))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        Todo todo = new Todo(NEW_LABEL, true, true);
        String jsonTodo = objectMapper.writeValueAsString(todo);
        mockMvc.perform(post(TODO_URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonTodo))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        Optional<Todo> optionalTodo = todoService.findById(ID);
        assertTrue(optionalTodo.isPresent());

        Todo todo = optionalTodo.get();
        todo.setLabel(NEW_LABEL);

        String jsonTodo = objectMapper.writeValueAsString(todo);
        String putUri = TODO_URI + "/" + ID;
        mockMvc.perform(put(putUri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonTodo))
                .andExpect(status().isOk());

        optionalTodo = todoService.findById(ID);
        assertTrue(optionalTodo.isPresent());
        todo = optionalTodo.get();
        assertEquals(NEW_LABEL, todo.getLabel());
    }

    @Test
    public void testDeleteById() throws Exception {
        Optional<Todo> todoOptional = todoService.findById(ID);
        assertTrue(todoOptional.isPresent());

        String deleteUri = TODO_URI + "/" + ID;
        mockMvc.perform(delete(deleteUri))
                .andExpect(status().isOk());

        todoOptional = todoService.findById(ID);
        assertFalse(todoOptional.isPresent());
    }
}
