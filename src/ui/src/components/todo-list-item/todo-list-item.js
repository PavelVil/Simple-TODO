import React, {Component} from 'react'
import './todo-list-item.css'

export default class TodoListItem extends Component {
    render() {
        const {label, active, important, onDelete, onToggleImportant, onToggleDone} = this.props;
        const classNames = ['todo-list-item', active ? '' : 'done', important ? 'important' : ''];
        console.log(label, classNames)
        return (
            <span className={classNames.join(' ')}>
                <span onClick={onToggleDone} className='todo-list-item-label'>{label}</span>
                <button onClick={onDelete} className="btn btn-outline-danger btn-sm float-right">
                    <i className="fa fa-trash-o"/>
                </button>
                <button onClick={onToggleImportant} className="btn btn-outline-danger btn-sm float-right">
                    <i className="fa fa-exclamation"/>
                </button>
            </span>
        )
    }
}