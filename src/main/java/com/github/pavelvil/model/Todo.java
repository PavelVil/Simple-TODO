package com.github.pavelvil.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Todo extends BaseModel {

    private String label;
    @Column(name = "active")
    private Boolean isActive = true;
    @Column(name = "important")
    private Boolean isImportant = false;

    public Todo() {

    }

    public Todo(String label) {
        this.label = label;
    }

    public Todo(String label, Boolean isActive, Boolean isImportant) {
        this.label = label;
        this.isActive = isActive;
        this.isImportant = isImportant;
    }

    public Todo(Todo todo) {
        Todo newTodo = new Todo(todo.getLabel(), todo.getActive(), todo.getImportant());
        newTodo.setId(todo.getId());
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "label='" + label + '\'' +
                ", isActive=" + isActive +
                ", isImportant=" + isImportant +
                ", id=" + id +
                '}';
    }
}
