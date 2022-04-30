import React from 'react';
import './styles/QueryInput.css'
import axios from 'axios';
import SearchResultList from "./SearchResultList";


export default class QueryInput extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            error: "",
            info: "",
            query: "",
            success: true,
            hideRes: false
        }

    };

    handleValidation() {
        let query = this.state.query;
        let error = "";
        let formIsValid = true;

        if (!query) {
            formIsValid = false;
            error = "Cannot be empty!";
        } else if (typeof query !== "undefined") {
            if (!query.match(/^[a-zA-Z() ]+$/)) {
                formIsValid = false;
                error = "Only letters and round brackets allows!";
            }
        }

        if (formIsValid)
            this.setState({info: "You entered: " + query})
        else {
            this.setState({info: ""})
            this.setState({success: false});
        }

        this.setState({error: error});
        this.setState({query: query});
        return formIsValid;
    }


    handleChange(field, e) {
        let query = e.target.value;
        this.setState({query});
    }

    contactSubmit(e) {
        this.setState({success: false})

        e.preventDefault();
        if (this.handleValidation()) {
            const query = {query: this.state.query};
            axios.post('http://localhost:8080/saveQuery', query)
                .then((response) => {
                    if (response.data === "Query accepted.")
                        this.setState({success: true})
                    else {
                        this.setState({error: "Bad query syntax!"})
                    }
                });
        }

        this.setState({hideRes: true})
        this.setState({query: ''})
    }

    render() {
        const syntaxError = !this.state.success
        return (
            <body className="My-body">
            <form onSubmit={this.contactSubmit.bind(this)}>
                <label/>
                {syntaxError &&
                    <div className="row justify-content-center">
                        <div className=" col-6 alert alert-danger alert-dismissible fade show" role="alert">
                            {this.state.error}
                        </div>
                    </div>
                }
                <label className="Query-label">Query:
                    <input placeholder="" className="My-Input" id="submit"
                           onChange={this.handleChange.bind(this, "info")} value={this.state.query}/>
                </label>
                <button className="Search-Button" id="submit" value="Submit">Submit</button>
                {!syntaxError && this.state.info &&
                    <div className="row justify-content-center">
                        <div className=" col-7 alert alert-secondary alert-dismissible fade show" role="alert">
                            {this.state.info}
                        </div>
                    </div>
                }
            </form>
            {!syntaxError && this.state.info &&
                <div className="SearchResultList">
                    <SearchResultList hideResults={this.state.hideRes}/>
                </div>
            }
            </body>
        );
    }

}


