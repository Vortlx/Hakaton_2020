/*!

=========================================================
* Light Bootstrap Dashboard React - v1.3.0
=========================================================

* Product Page: https://www.creative-tim.com/product/light-bootstrap-dashboard-react
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/light-bootstrap-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";
import { NavItem, Nav, NavDropdown, MenuItem } from "react-bootstrap";

import Card from "components/Card/Card.jsx";
import {thArray, tdArray, host} from "variables/Variables.jsx";
import {isClient} from "../helpers/helper";
import {hostSocket} from "../variables/Variables";

import Loader from 'react-loader-spinner'
class TableList extends Component {

  constructor() {
    super();

    this.state = {isClient : isClient(),
                  calls :[],
        services:[],
                showSpinner: false
    }


  }


  componentDidMount() {
    if (this.state.isClient) {

      const requestOptions = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Kfmn ' + localStorage.getItem('token')
        },
      };

      fetch(host + `/client/services`, requestOptions)
          .then(response => {
            console.log(response.json()
                .then(user => {
                    this.setState({services:user})
                  console.log(user);

                }))
          });

    } else {

      this.connection = new WebSocket(hostSocket + '/socket/websocket');

let sock= this.connection;
let _this = this;

      this.connection.onopen = function () {

        var command = "SUBSCRIBE";
        var vspId = "xyi xyi xyi";

        console.log("connect");

        var send = {
          command: command,
          message: localStorage.getItem("login")
        }
        sock.send(JSON.stringify(send));


      }

      this.connection.onmessage = function (e){
        console.log(e);
        console.log(e.data);


        console.log("66666666666666");
        console.log(typeof e.data);

        let data  = JSON.parse(e.data);
        console.log(data);

        console.log(JSON.parse(data.message));


        switch (data.command){
          case "LIST":{
              console.log("2222222");
              console.log(data.message);
            _this.setState({calls:JSON.parse(data.message)})
            break;
          }
        }
      }


    }
  }

  socketPrivet(){

  }

    createMeeting(serviceId) {
        this.setState({showSpinner: true});

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Kfmn ' + localStorage.getItem('token'),
                'Authentication': 'Kfmn ' + localStorage.getItem('token')
            },
            body: ""
        };
        fetch(host + "/client/services/" + serviceId + "/call" , requestOptions)
            .then(response => {
                response.json().then(json => {
                    this.setState({showSpinner: false});
                    console.log(json);
                    window.location.replace(json.url);
                }).catch(e=>{
                    this.setState({showSpinner: false});
                })
            }).catch(e=>{
            this.setState({showSpinner: false});
        });


    }
send(){


      this.connection.send("12345")

  this.setState({showSpinner: true});


  }
    joinMeeting(meetingId) {
      // alert

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Kfmn ' + localStorage.getItem('token'),
                'Authentication': 'Kfmn ' + localStorage.getItem('token')
            },
            body: ""
        };
        fetch(host + "/employee/services/" + meetingId + "/join" , requestOptions)
            .then(response => {
                response.json().then(json => {
                    console.log(json);
                    window.location.replace(json.url);
                });
            });
    }


  render() {
    return (
      <div className="content">
        <Grid fluid>
          <Row>
              { this.state.showSpinner &&
              <div className={"spinner-wrapper"}>
              <div className={"spinner"}>
                <Loader
                  type="Puff"
                  color="#00BFFF"
                  height={100}
                  width={100}
                  timeout={90000} //3 secs
                 />
              </div>
              </div>}
            {this.state.isClient &&
                <div>
            <Col md={12}>
              <Card
                title={this.state.isClient ?"Услуги" : "Заявки"}
                category=""
                ctTableFullWidth
                ctTableResponsive
                content={
                    <div>
                        {/*<div>*/}
                        {/*    <Nav pullRight>*/}
                        {/*        <NavItem onClick={()=>{this.createMeeting()}} event Key={3} href="#">*/}
                        {/*            Создать встречу*/}
                        {/*        </NavItem>*/}
                        {/*    </Nav>*/}
                        {/*</div>*/}

                        <div className={"list-group"}>{this.printServices()}</div>
                    </div>
                }
              />
            </Col>

                </div>}
            {!this.state.isClient &&
            <div>
                <Nav pullRight>
                    <NavItem onClick={()=>{this.joinMeeting()}} event Key={3} href="#">
                        Присоединиться к встрече
                    </NavItem>
                </Nav>

              <Col md={12}>
                <Card
                    title="Заявки"
                    category="Выберите заявку для звонка"
                    ctTableFullWidth
                    ctTableResponsive
                    content={
                      <div>
                          <div className={"list-group"}>{this.printCalls()}</div>
                      </div>
                    }
                />
              </Col>

            </div>}
          </Row>
        </Grid>
      </div>
    );
  }

  printCalls() {
    let result = [];

    console.log("5555555555");
    console.log(this.state.calls);
    console.log(this.state.calls[0]);


    for(let i=0;i<this.state.calls.length;i++){
      // result.push(<li onClick={()=>alert(i)} className={"list-group-item"}>i</li>);
      result.push(
          <a href="#" onClick={()=>this.joinMeeting(this.state.calls[i].id)} className="list-group-item list-group-item-action flex-column align-items-start">
            <div className="d-flex w-100 justify-content-between">
              <h5 className="mb-1">{this.state.calls[i].service.name}</h5>
              <small className="text-muted">{this.state.calls[i].dateTime}</small>
            </div>
            <p className="mb-1">{this.state.calls[i].client.login}</p>
          </a>


      );
    }

return result;
  }

    printServices() {
        let result = [];
        console.log(444)
        console.log(this.state.services);
        for(let i=0;i<this.state.services.length;i++){
            // result.push(<li onClick={()=>alert(i)} className={"list-group-item"}>i</li>);
            result.push(
                <div className="list-group-item list-group-item-action flex-column align-items-start">
                    <div className="d-flex w-100 justify-content-between">
                        <h5 className="mb-1">{this.state.services[i].name}</h5>
                        {/*<small className="text-muted">{this.state.services[i].name}</small>*/}
                    </div>
                    <a href="#" onClick={()=>this.createMeeting(this.state.services[i].id)}>
                        <p className="mb-1">Позвонить</p>
                    </a>
                </div>


            );
        }

        return result;
    }

}

export default TableList;
