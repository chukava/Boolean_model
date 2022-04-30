import React from 'react';
import axios from 'axios';
import './styles/Timediff.css'

export default class TimeDiff extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data_set: "", // l or s
            res: {},
            ready: false
        };
    }

    componentDidMount() {
        const data_set = this.props.dataSet
        this.setState(
            {data_set: this.props.dataSet}
        )
        console.log(data_set)
    }

    getTimeDiff(e) {
        e.preventDefault();
        let url
        if (this.state.data_set === "s")
            url = `http://localhost:8080/getTimeDifference-s`
        else
            url = `http://localhost:8080/getTimeDifference-l`

        axios.get(url)
            .then(result => {
                const res = result.data;
                console.log(result.data)
                this.setState({res});
            })

        console.log(this.state.res.diff)
        this.setState({ready: true})
    }


    render() {
        let quotient = Math.floor(this.state.res.timeSequence / this.state.res.timeBoolean);
        return (
            <div>
                <form onSubmit={this.getTimeDiff.bind(this)}>
                    <button className="Time-button" value="Submit">Show And Compare Search Speed</button>
                </form>
                {this.state.ready &&
                    <div>
                        <ul className="My-table">
                            <p className="My-p">Search time using boolean model:</p>
                            <li className="My-row"><strong>{this.state.res.timeBoolean} ns.</strong></li>
                            <p className="My-p">Search tim using sequential search:</p>
                            <li className="My-row"><strong>{this.state.res.timeSequence} ns.</strong></li>
                            <p className="My-p">Time difference between first and second:</p>
                            <li className="My-row"><strong>{this.state.res.diff} ns. = {quotient}x</strong></li>
                        </ul>
                        <hr/>
                    </div>
                }
            </div>
        );
    }

}