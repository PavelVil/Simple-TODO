import React from 'react'
import TodoListItem from '../todo-list-item'
import './todo-list.css'

const TodoList = ({todoData, onDelete, onToggleImportant, onToggleDone}) => {
    const elements = todoData.map(item => {
        const {id} = item;
        return (
            <li key={id} className="list-group-item">
                <TodoListItem
                    {...item}
                    onDelete={() => {
                        onDelete(id)
                    }}
                    onToggleImportant={() => onToggleImportant(id)}
                    onToggleDone={() => onToggleDone(id)}/>
            </li>)
    });

    if (todoData && todoData.length > 0) {
        return (
            <ul className="list-group todo-list">
                {elements}
            </ul>
        );
    }
    else {
        return <p>Data is empty</p>
    }
};

export default TodoList