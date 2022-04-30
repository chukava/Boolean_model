import React from 'react';
import './styles/QueryInput.css'
import axios from 'axios';


export default class QueryInput extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            fields: {},
            errors: {},
            query: "",
            success: true
        }
    };

    handleValidation(){
        let fields = this.state.fields;
        let errors = {};
        let formIsValid = true;
        let query = ""

        if(!fields["query"]){
            formIsValid = false;
            errors["query"] = "Cannot be empty!";
        }

        if(typeof fields["query"] !== "undefined"){
            if(!fields["query"].match(/^[a-zA-Z() ]+$/)){
                formIsValid = false;
                errors["query"] = "Only letters and round brackets allows!";
            }
        }

        if(formIsValid)
            this.setState({query: "You entered: " + fields["query"]})
        else
            this.setState({query: ""})

        this.setState({errors: errors});
        this.setState({fields: {}})
        return formIsValid;
    }


    handleChange(field, e){
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({fields});
    }

    contactSubmit(e){
        e.preventDefault();
        if(this.handleValidation()){
            const query = { query: this.state.fields["query"] };
            axios.post('http://localhost:8080/saveQuery', query)
                .then((response) => {
                    {
                        if(response.data === "Query accepted.")
                            this.setState({ success: true})
                        else
                            this.setState({ success: false})
                    }
                });
            this.setState({ query: ''})
        }else{
            // alert("Form has errors.")
        }

        if(!this.state.success)
            alert("Form has errors.")

    }

    render() {
        return (
            <form onSubmit= {this.contactSubmit.bind(this)}>
                <p className="error">{this.state.errors["query"]}</p>
                <label className="Query-label">Query:
                    <input placeholder="" className="My-Input" id="submit"  type="text" onChange={this.handleChange.bind(this, "query")} value={this.state.fields["query"]}/>
                </label>
                <button className="Search-Button" id="submit" value="Submit">Submit</button>
                <p claclassName="lol">{this.state.query}</p>
            </form>
        );
    }

}


