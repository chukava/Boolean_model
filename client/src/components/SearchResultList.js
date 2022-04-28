import React from 'react';
import axios from 'axios';

export default class SearchResultList extends React.Component {
    state = {
        persons: []
    }

    componentDidMount() {
        axios.get(`http://localhost:8080/boolean-model/result`)
            .then(res => {
                const persons = res.data;
                this.setState({ persons });
            })
    }

    render() {
        return (
            <ul>
                {
                    this.state.persons
                        .map(person =>
                            <li key={person.fileId}>{person.fileId}</li>
                        )
                }
            </ul>
        )
    }
}