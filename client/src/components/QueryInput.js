import React from 'react';
import './styles/QueryInput.css'

export default class QueryInput extends React.Component {

    constructor(props){
        super(props);

        this.state = {
            fields: {},
            errors: {},
            query: ""
        }
    }

    inputCheck(e) {
        const re = /[A-Za-z() ]+/g;
        if (!re.test(e.key)) {
            e.preventDefault();
        }
    }

    handleValidation(){
        let fields = this.state.fields;
        let errors = {};
        let formIsValid = true;
        let query = ""

        //Name
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
            // alert("Form submitted");
        }else{
            // alert("Form has errors.")
        }

    }



    render() {
        return (
            <form onSubmit= {this.contactSubmit.bind(this)}>
                <p className="error">{this.state.errors["query"]}</p>
                <label className="Query-label">Query:
                    <input className="My-Input" type="text" onChange={this.handleChange.bind(this, "query")} value={this.state.fields["query"]}/>
                </label>
                <button className="Search-Button" id="submit" value="Submit">Submit</button>
                <p className="lol">{this.state.query}</p>
            </form>
        );
    }

}


