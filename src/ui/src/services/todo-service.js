export default class TodoService {

    _apiUrl = 'http://localhost:8080/api';
    _headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    getData = async (url) => {
        const res = await fetch(`${this._apiUrl}${url}`);

        if (!res.ok) {
            throw new Error(`Could not fetch ${url}` +
                `, received ${res.status}`)
        }

        return await res.json();
    };

    getAll = async () => {
        const todos = await this.getData('/todos');
        return todos.map(this._getTodoObject);
    };

    getById = async (id) => {
        const todo = await this.getData(`/todo/${id}`);
        return this._getTodoObject(todo);
    };

    getByLabel = async (label) => {
        const todos = await this.getData(`/todos/${label}`);
        return todos.map(this._getTodoObject);
    };

    deleteById = async (id) => {
        return await fetch(`${this._apiUrl}/todo/${id}`, {
            method: 'DELETE',
            headers: this._headers
        })
    };

    update = async (todo) => {
        return await fetch(`${this._apiUrl}/todo/${todo.id}`, {
            method: 'PUT',
            headers: this._headers,
            body: JSON.stringify(todo)
        })
    };

    create = async (todo) => {
        const created = await fetch(`${this._apiUrl}/todo`, {
            method: 'POST',
            headers: this._headers,
            body: JSON.stringify(todo)
        });
        return await created.json();
    };

    _getTodoObject = (todo) => {
        return {
            ...todo
        }
    }
}