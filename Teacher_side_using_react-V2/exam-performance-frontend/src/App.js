import React, { Component } from "react";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import injectTapEventPlugin from "react-tap-event-plugin";

import logo from "./logo.svg";
import "./App.css";
import Form from "./Form";
import Table from "./Table";

injectTapEventPlugin();

class App extends Component {
  state = {
    data: []
  };
  
  render() {
    return (
      <MuiThemeProvider>
        <div className="App">
          <Form
            onSubmit={submission =>
              this.setState({
                data: [...this.state.data, submission]
              })
            }
          />
          <Table
            data={this.state.data}
            header={[
              {
                name: "Student ID",
                prop: "studentId"
              },
              {
                name: "Student Name",
                prop: "studentName"
              },
              {
                name: "Exam Type",
                prop: "examType"
              },
              {
                name: "Subject-1",
                prop: "sub1"
              },
              {
                name: "Subject-2",
                prop: "sub2"
              },
              {
                name: "Subject-3",
                prop: "sub3"
              },
              {
                name: "Subject-4",
                prop: "sub4"
              },
              {
                name: "Total",
                prop: "total"
              },
              {
                name: "Percentage",
                prop: "percentage"
              },
              {
                name: "Remarks",
                prop: "remarks"
              }
            ]}
          />
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
