import React from "react";
import TextField from "material-ui/TextField";
import RaisedButton from "material-ui/RaisedButton";
import ReactDOM from 'react-dom';
import App from './App';
//import { useTable } from 'react-table';

export default class Form extends React.Component {
  state = {
    studentId: "",
    studentIdError: "",
    studentName: "",
    studentNameError: "",
    examType: "",
    examTypeError: "",
    remarks: "",
    remarksError: "",
    sub1: "",
    sub2: "",
    sub3: "",
    sub4: "",
    total: "",
    percentage: ""
  };

  change = e => {
    // this.props.onChange({ [e.target.name]: e.target.value });
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  validate = () => {
    let isError = false;
    const errors = {
      studentIdError: "",
      studentNameError: "",
      examTypeError: "",
      remarksError: "",
      // passwordError: ""
    };

    // if (this.state.examType.length < 5) {
    //   isError = true;
    //   errors.examTypeError = "examType needs to be atleast 5 characters long";
    // }

    // if (this.state.remarks.indexOf("@") === -1) {
    //   isError = true;
    //   errors.remarksError = "Requires valid remarks";
    // }

    this.setState({
      ...this.state,
      ...errors
    });

    return isError;
  };

  

  onSubmit = e => {
    // ReactDOM.render(<App />, document.getElementById('root'));
    // e.preventDefault();
    const err = this.validate();
    console.log("Onsubmit: ", this.state);
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(this.state)
    };
    let student_id = this.state.studentId;
    fetch(`http://localhost:8000/user/register`, requestOptions)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                // App.reload();
                // window.location.reload(false);
                if (!err) {
                  fetch(`http://localhost:8000/getUsers`)
                        .then(res => res.json())
                        .then(data => {
                            console.log("getUsers: ", data);
                            data.forEach(student => {
                              student.studentId = student.student_id;
                              student.studentName = student.student_name;
                              student.examType = student.exam_type;
                              console.log("student.studentId: ", student.studentId);
                              console.log("student_id: ", student_id);
                              console.log(student.studentId === student_id);
                              if(student.studentId === student_id){
                                this.props.onSubmit(student);
                              }
                              // this.props.onSubmit(student);
                            })
                            // this.props.onSubmit(data);
                        })
                  // clear form
                  this.setState({
                    studentId: "",
                    studentIdError: "",
                    studentName: "",
                    studentNameError: "",
                    examType: "",
                    examTypeError: "",
                    remarks: "",
                    remarksError: "",
                    sub1: "",
                    sub2: "",
                    sub3: "",
                    sub4: "",
                    total: "",
                    percentage: ""
                    // password: "",
                    // passwordError: ""
                  });
                }
            })
  };

  render() {
    return (
      <form>
        <TextField
          name="studentId"
          hintText="Student ID"
          floatingLabelText="Student ID"
          value={this.state.studentId}
          onChange={e => this.change(e)}
          errorText={this.state.studentIdError}
          floatingLabelFixed
        />
        {/* <br /> */}
        <TextField
          name="studentName"
          hintText="Student Name"
          floatingLabelText="Student Name"
          value={this.state.studentName}
          onChange={e => this.change(e)}
          errorText={this.state.studentNameError}
          floatingLabelFixed
        />
        <br />
        <TextField
          name="examType"
          hintText="Exam Type"
          floatingLabelText="Exam Type"
          value={this.state.examType}
          onChange={e => this.change(e)}
          errorText={this.state.examTypeError}
          floatingLabelFixed
        />
        {/* <br /> */}
        <TextField
          name="sub1"
          hintText="Subject-1"
          floatingLabelText="Subject-1"
          value={this.state.sub1}
          onChange={e => this.change(e)}
          errorText={this.state.sub1Error}
          floatingLabelFixed
        />
        <br />
        <TextField
          name="sub2"
          hintText="Subject-2"
          floatingLabelText="Subject-2"
          value={this.state.sub2}
          onChange={e => this.change(e)}
          errorText={this.state.sub2Error}
          floatingLabelFixed
        />
        {/* <br /> */}
        <TextField
          name="sub3"
          hintText="Subject-3"
          floatingLabelText="Subject-3"
          value={this.state.sub3}
          onChange={e => this.change(e)}
          errorText={this.state.sub3Error}
          floatingLabelFixed
        />
        <br />
        <TextField
          name="sub4"
          hintText="Subject-4"
          floatingLabelText="Subject-4"
          value={this.state.sub4}
          onChange={e => this.change(e)}
          errorText={this.state.sub4Error}
          floatingLabelFixed
        />
        {/* <br /> */}
        {/* <TextField
          name="total"
          hintText="Total"
          floatingLabelText="Total"
          value={this.state.total}
          onChange={e => this.change(e)}
          errorText={this.state.totalError}
          floatingLabelFixed
        />
        <br />
        <TextField
          name="percentage"
          hintText="Percentage"
          floatingLabelText="Percentage"
          value={this.state.percentage}
          onChange={e => this.change(e)}
          errorText={this.state.percentageError}
          floatingLabelFixed
        />
        <br /> */}
        <TextField
          name="remarks"
          hintText="Remarks"
          floatingLabelText="Remarks"
          value={this.state.remarks}
          onChange={e => this.change(e)}
          errorText={this.state.remarksError}
          floatingLabelFixed
        />
        <br />
        <RaisedButton label="Submit" onClick={e => this.onSubmit(e)} primary />
      </form>
    );
  }
}
