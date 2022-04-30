import React from 'react';
import axios from 'axios';
import './styles/SearchResult.css'
import TimeDiff from "./TimeDiff";

export default class SearchResultList extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            boolean_results_s: [],
            sequence_results_s: [],
            boolean_results_l: [],
            sequence_results_l: [],
            data_s: false,
            data_l: false
        };
    }

    getResultsS(e) {
        e.preventDefault();
        axios.get(`http://localhost:8080/boolean-model/getResult-s`)
            .then(result => {
                const boolean_results_s = result.data;
                console.log(result.data)

                this.setState({boolean_results_s});
            })
        axios.get(`http://localhost:8080/sequence-search/getResult-s`)
            .then(result => {
                const sequence_results_s = result.data;
                this.setState({sequence_results_s});
            })
        this.setState({data_s: true})
    }

    getResultsL(e) {
        e.preventDefault();
        axios.get(`http://localhost:8080/boolean-model/getResult-l`)
            .then(result => {
                const boolean_results_l = result.data;
                this.setState({boolean_results_l});
            })
        axios.get(`http://localhost:8080/sequence-search/getResult-l`)
            .then(result => {
                const sequence_results_l = result.data;
                this.setState({sequence_results_l});
            })
        this.setState({data_l: true})
    }


    render() {
        return (
            <div className="container">
                <div className="container">
                    <div className="row">
                        <div className="col-6">
                            <div className="col-12">
                                <form onSubmit={this.getResultsS.bind(this)}>
                                    <button className="Search-Button-R" id="submit" value="Submit">Search in small data
                                        set
                                    </button>
                                </form>
                            </div>
                        </div>
                        <div className="col-6">
                            <div className="col-12">
                                <form onSubmit={this.getResultsL.bind(this)}>
                                    <button className="Search-Button-R" id="submit" value="Submit">Search in large data
                                        set
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <label></label>
                    <div className="row  justify-content-center">
                        {this.state.data_s &&
                            <div className="col-6">
                                <div className="col-12 border bg-light ">
                                    <p className="My-Text">Small data set of <strong>200</strong> files processed</p>
                                    <span className="My-Text">Sequential Search</span>
                                    <div className="vr"></div>
                                    <span className="My-Text">Boolean Search</span>
                                    <p></p>
                                    <span className="My-Text">Total found: <strong>{this.state.boolean_results_s.length}</strong></span>

                                    <hr/>
                                    <TimeDiff dataSet={"s"}/>
                                    <div className="row">
                                        <div className="col-6">
                                            <div className="dropdown">
                                                <button className="dropdown-toggle Search-Button-R" type="button"
                                                        id="dropdownMenuButton1" data-bs-toggle="dropdown"
                                                        aria-expanded="false">
                                                    Dropdown button
                                                </button>
                                                <ul className="dropdown-menu My-table"
                                                    aria-labelledby="dropdownMenuButton1">
                                                    {
                                                        this.state.boolean_results_s
                                                            .map(file =>
                                                                <li><a className="dropdown-item"
                                                                       href="#">{file.fileId}</a></li>
                                                            )
                                                    }
                                                </ul>
                                            </div>
                                        </div>
                                        <div className="col-6">
                                            <button className="Search-Button-R">Print ids</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        }
                        {this.state.data_l &&
                            <div className="col-6">
                                <div className="col-12 border bg-light ">
                                    <p className="My-Text">Large data set of <strong>2000</strong> files processed</p>
                                    <span className="My-Text">Sequential Search </span>
                                    <div className="vr"></div>
                                    <span className="My-Text">Boolean Search</span>
                                    <p></p>
                                    <span className="My-Text">Total found: <strong>{this.state.boolean_results_l.length}</strong></span>
                                    <div className="vr"></div>
                                    {/*<span className="My-Text">Total found: {this.state.sequence_results_l.length}</span>*/}
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
                        }
                    </div>
                </div>
            </div>
        )
    }
}