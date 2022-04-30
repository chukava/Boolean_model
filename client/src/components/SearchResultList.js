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
            data_l: false,
            printData_s: false,
            printData_l: false
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

    printDataS(e) {
        e.preventDefault();
        this.setState({printData_s: true})
    }

    printDataL(e) {
        e.preventDefault();
        this.setState({printData_l: true})
    }


    render() {
        return (
            <div className="container">
                <div className="container">
                    <div className="row">
                        <div className="col-6">
                            <div className="col-12">
                                <form onSubmit={this.getResultsS.bind(this)}>
                                    <button className="Search-Button-R" id="submit" value="Submit">Search In Small Sata
                                        Set
                                    </button>
                                </form>
                            </div>
                        </div>
                        <div className="col-6">
                            <div className="col-12">
                                <form onSubmit={this.getResultsL.bind(this)}>
                                    <button className="Search-Button-R" id="submit" value="Submit">Search In Large Data
                                        Set
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="container">
                    <label/>
                    <div className="row  justify-content-center">
                        {this.state.data_s &&
                            <div className="col-6">
                                <div className="col-12 border bg-light ">
                                    <p className="My-Text">Small Data Set Of <strong>200</strong> Files Processed</p>
                                    <span className="My-Text">Sequential Search</span>
                                    <div className="vr"/>
                                    <span className="My-Text">Boolean Search</span>
                                    <p/>
                                    <span
                                        className="My-Text">Total found: <strong>{this.state.boolean_results_s.length}</strong></span>
                                    <hr/>
                                    <TimeDiff dataSet={"s"}/>
                                    <div className="row">
                                        <form onSubmit={this.printDataS.bind(this)}>
                                            <button className="Time-button" value="Submit">Print List Of Files</button>
                                        </form>
                                        {this.state.printData_s &&
                                            <div>
                                                <ul className="My-table">
                                                    {
                                                        this.state.boolean_results_s
                                                            .map(file =>
                                                                <li className="My-row"
                                                                    key={file.fileId}>fileId: <strong>{file.fileId}</strong></li>
                                                            )
                                                    }
                                                </ul>
                                            </div>
                                        }
                                    </div>
                                </div>
                            </div>
                        }
                        {this.state.data_l &&
                            <div className="col-6">
                                <div className="col-12 border bg-light ">
                                    <p className="My-Text">Large Data Set Of <strong>2000</strong> Files Processed</p>
                                    <span className="My-Text">Sequential Search </span>
                                    <div className="vr"/>
                                    <span className="My-Text">Boolean Search</span>
                                    <p/>
                                    <span
                                        className="My-Text">Total found: <strong>{this.state.boolean_results_l.length}</strong></span>
                                    <hr/>
                                    <TimeDiff dataSet={"l"}/>
                                    <div className="row">
                                        <form onSubmit={this.printDataL.bind(this)}>
                                            <button className="Time-button" value="Submit">Print List Of Files</button>
                                        </form>
                                        {this.state.printData_l &&
                                            <div>
                                                <ul className="My-table">
                                                    {
                                                        this.state.boolean_results_l
                                                            .map(file =>
                                                                <li className="My-row"
                                                                    key={file.fileId}>fileId: <strong>{file.fileId}</strong></li>
                                                            )
                                                    }
                                                </ul>
                                            </div>
                                        }
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