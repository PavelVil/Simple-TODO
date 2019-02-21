import React from 'react'
import './todo-filter.css'

const TodoFilter = ({activeFilter, onFilterChange}) => {
    const buttons = [
        {filter: 'all', label: 'All'},
        {filter: 'active', label: 'Active'},
        {filter: 'done', label: 'Done'}
    ];
    const btnElements = buttons.map(btn => {
        const btnClass = btn.filter === activeFilter ? 'btn btn-info' : 'btn';
        return (
            <button className={btnClass}
                    key={btn.filter}
                    onClick={() => onFilterChange(btn.filter)}>{btn.label}
            </button>
        )
    });

    return (
        <div>
            {btnElements}
        </div>
    )
};

export default TodoFilter