import React, {Component} from "react";
import {BrowserRouter, Redirect, Route, Switch,Router} from "react-router-dom";
import AdminLayout from "./layouts/Admin";
import Login from "./layouts/Login";
import JItsiConferece from "./layouts/JItsiConferece";

export class App extends Component {
    constructor() {
        super();
        this.setAuth = this.setAuth.bind(this);
        this.state = {
            auth: this.hasUser()
        };
    }

    hasUser() {
        return localStorage.getItem('user') != null;
    }

    setAuth(value){
        console.log(222)
        console.log(this)
        console.log(this.state)
        this.setState({auth:value})
    }

    render() {
        return ((this.state.auth) ?


                <BrowserRouter>
                    <Switch>
                        <Route path="/admin/fishingsite/:roomName" render={
                            props => <JItsiConferece {...props} action={this.setAuth} />
                        }/>
                        <Route path="/admin" render={
                            props => <AdminLayout {...props} action={this.setAuth} />

                        }/>
                        <Redirect from="/" to="/admin/table"/>
                    </Switch>
                </BrowserRouter>

                    :

                    <BrowserRouter>
                        <Switch>
                            <Route path="/login" render={


                                props => <Login {...props}  action={this.setAuth}/>

                            }/>
                            <Redirect from="/" to="/login"/>
                        </Switch>
                    </BrowserRouter>





        );
    }
}

export default App;
