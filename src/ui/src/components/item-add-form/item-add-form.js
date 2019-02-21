import React, {Component} from 'react'
import './item-add-form.css'

export default class ItemAddForm extends Component {

    state = {
        todoLabel: ""
    };

    onToggleInput = (event) => {
        const todoLabel = event.target.value;
        this.setState({todoLabel})
    };

    onSubmit = (event) => {
        event.preventDefault();
        const {onAddTodo} = this.props;
        const {todoLabel} = this.state;
        onAddTodo(todoLabel);
        this.setState({todoLabel: ""})
    };

    render() {
        const {todoLabel} = this.state;
        return (
            <form className="item-add-form d-flex item-add-form" onSubmit={this.onSubmit}>
                <input className="form-control"
                       type="text"
                       placeholder="Add new todo"
                       value={todoLabel}
                       onChange={this.onToggleInput}/>
                <button className="btn btn-outline-secondary">Create</button>
            </form>
        )
    }
}