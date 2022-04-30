import React from 'react';
import axios from 'axios';
import'./styles/SearchResult.css'

export default class SearchResultList extends React.Component {
    state = {
        boolean_results_s: [],
        sequence_results_s: [],
        boolean_results_l: [],
        sequence_results_l: []
    }

    componentDidMount() {
        axios.get(`http://localhost:8080/boolean-model/getResult-s`)
            .then(result => {
                const boolean_results_s = result.data;
                this.setState({ boolean_results_s });
            })
        axios.get(`http://localhost:8080/sequence-search/getResult-s`)
            .then(result => {
                const sequence_results_s = result.data;
                this.setState({ sequence_results_s });
            })
        axios.get(`http://localhost:8080/boolean-model/getResult-l`)
            .then(result => {
                const boolean_results_l = result.data;
                this.setState({ boolean_results_l });
            })
        axios.get(`http://localhost:8080/sequence-search/getResult-l`)
            .then(result => {
                const sequence_results_l = result.data;
                this.setState({ sequence_results_l });
            })
    }

    render() {
        return (
            <div class="container">
                <label></label>
                <div className="row">
                    <div className="col-6">
                        <div className="col-12 border bg-light ">
                            <p className="My-Text">Small data set of 200 files processed</p>
                            <span className="My-Text" >Sequential Search</span>
                            <div className="vr"></div>
                            <span className="My-Text" >Boolean Search</span>
                            <p></p>
                            <span className="My-Text" >Total found: {this.state.boolean_results_s.length}</span>
                            <div className="vr"></div>
                            <span className="My-Text" >Total found: {this.state.sequence_results_s.length}</span>
                            <hr/>
                            <div className="row">
                                <div className="col-5">
                                    <ul className="My-table">
                                        {
                                            this.state.boolean_results_s
                                                .map(file =>
                                                    <li className="My-row" key={file.fileId}>{file.fileId}</li>
                                                )
                                        }
                                    </ul>
                                </div>
                                <div className="col-5">
                                    <ul className="My-table">
                                        {
                                            this.state.sequence_results_s
                                                .map(file =>
                                                        <li className="My-row" key={file.fileId}>{file.fileId}</li>
                                                )
                                        }
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="col-12 border bg-light ">
                            <p className="My-Text">Small data set of 200 files processed</p>
                            <span className="My-Text" >Sequential Search</span>
                            <div className="vr"></div>
                            <span className="My-Text" >Boolean Search</span>
                            <p></p>
                            <span className="My-Text" >Total found: {this.state.boolean_results_l.length}</span>
                            <div className="vr"></div>
                            <span className="My-Text" >Total found: {this.state.sequence_results_l.length}</span>
                            <hr/>
                            <div className="row">
                                <div className="col-5">
                                    <ul className="My-table">
                                        {
                                            this.state.boolean_results_l
                                                .map(file =>
                                                    <li className="My-row" key={file.fileId}>{file.fileId}</li>
                                                )
                                        }
                                    </ul>
                                </div>
                                <div className="col-5">
                                    <ul className="My-table">
                                        {
                                            this.state.sequence_results_l
                                                .map(file =>
                                                    <li className="My-row" key={file.fileId}>{file.fileId}</li>
                                                )
                                        }
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}