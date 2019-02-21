import React, {Component} from 'react'
import Header from '../header'
import SearchPanel from "../search-panel/search-panel"
import './app.css'
import TodoFilter from "../todo-filter/todo-filter";
import TodoList from "../todo-list/todo-list";
import ItemAddForm from "../item-add-form/item-add-form";
import TodoService from "../../services/todo-service";

export default class App extends Component {

    constructor(props) {
        super(props);
        this.todoService = new TodoService();
        this.state = {
            todoData: [],
            labelForSearch: "",
            activeFilter: 'all'
        }
    }

    componentDidMount() {
        this.todoService.getAll()
            .then(todoData => {
                this.setState({todoData})
            })
    }

    deleteTodo = (id) => {
        this.todoService.deleteById(id)
            .then(() => {
                this.setState(({todoData}) => {
                    return {
                        todoData: todoData.filter(item => item.id !== id)
                    }
                })
            })
    };

    createTodo = (label) => {
        let newTodo = this._createTodoFromLabel(label);
        this.todoService.create(newTodo)
            .then(todo => {
                newTodo = {...newTodo, id: todo.id};
                this.setState(({todoData}) => {
                    return {
                        todoData: [...todoData, newTodo]
                    }
                })
            })
    };

    onToggleImportant = (id) => {
        this._onToggleProperty(id, 'important');
    };

    onToggleDone = (id) => {
        this._onToggleProperty(id, 'active');
    };

    onSearchLabelChange = (labelForSearch) => {
        this.setState({labelForSearch})
    };

    onFilterChange = (activeFilter) => {
        this.setState({activeFilter});
    };

    _filterData = (data) => {
        const {activeFilter} = this.state;
        switch (activeFilter) {
            case 'all':
                return data;
            case 'active':
                return data.filter(item => item.active);
            case 'done':
                return data.filter(item => !item.active);
            default:
                return data;
        }
    };

    _searchData = (data, labelForSearch) => {
        return labelForSearch && labelForSearch.length === 0 ? data : data.filter(item => item.label.toLowerCase().indexOf(labelForSearch.toLowerCase()) !== -1);
    };

    _updateTodoProperty = (arr, id, propName) => {
        const index = arr.findIndex(item => item.id === id);
        const oldTodo = arr[index];
        const updatedTodo = {
            ...oldTodo,
            [propName]: !oldTodo[propName]
        };
        return [
            ...arr.slice(0, index),
            updatedTodo,
            ...arr.slice(index + 1)
        ]
    };

    _onToggleProperty = (id, propName) => {
        const {todoData} = this.state;
        const newData = this._updateTodoProperty(todoData, id, propName);
        const index = newData.findIndex(item => item.id === id);
        this.todoService.update(newData[index])
            .then(() => {
                this.setState({
                    todoData: newData
                })
            });
    };

    _createTodoFromLabel = (label) => {
        return {
            label,
            active: true,
            important: false
        }
    };


    render() {
        const {todoData, labelForSearch, activeFilter} = this.state;
        const visibleData = this._filterData(this._searchData(todoData, labelForSearch), activeFilter);
        return (
            <div className="main-box">
                <Header/>
                <div>
                    <SearchPanel onSearchLabelChange={this.onSearchLabelChange}/>
                    <TodoFilter activeFilter={activeFilter} onFilterChange={this.onFilterChange}/>
                </div>
                <TodoList
                    todoData={visibleData}
                    onToggleDone={this.onToggleDone}
                    onToggleImportant={this.onToggleImportant}
                    onDelete={this.deleteTodo}/>
                <ItemAddForm onAddTodo={this.createTodo}/>
            </div>
        )
    }
};
