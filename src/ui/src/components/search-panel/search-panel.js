import React, {Component} from 'react'
import './search-panel.css'

export default class SearchPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchLabel: ""
        }
    };

    onSearchLabelChange = (event) => {
        const searchLabel = event.target.value;
        this.setState({searchLabel});
        this.props.onSearchLabelChange(searchLabel);
    };

    render() {
        return (
                <input className="form-control search-input"
                    placeholder="Search Todos"
                    onChange={this.onSearchLabelChange}/>
        )
    }
}