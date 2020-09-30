import React, {Component} from "react";
import {Route, Switch} from "react-router-dom";

import Footer from "components/Footer/Footer";
import Sidebar from "components/Sidebar/Sidebar";
import routes from "routes.js";
import image from "assets/img/sidebar-3.jpg";
import {Jutsu} from "react-jutsu";
import {host} from "../variables/Variables";

class JItsiConferece extends Component {
    constructor(props) {
        super(props);
        this.state = {
            _notificationSystem: null,
            image: image,
            color: "black",
            hasImage: true,
            fixedClasses: "dropdown show-dropdown open",
            serviceName: "",
            clientLogin: "",
            employeeLogin: ""
        };
    }

    handleNotificationClick = position => {
        var color = Math.floor(Math.random() * 4 + 1);
        var level;
        switch (color) {
            case 1:
                level = "success";
                break;
            case 2:
                level = "warning";
                break;
            case 3:
                level = "error";
                break;
            case 4:
                level = "info";
                break;
            default:
                break;
        }
        this.state._notificationSystem.addNotification({
            title: <span data-notify="icon" className="pe-7s-gift"/>,
            message: (
                <div>
                    Welcome to <b>Light Bootstrap Dashboard</b> - a beautiful freebie for
                    every web developer.
                </div>
            ),
            level: level,
            position: position,
            autoDismiss: 15
        });
    };

    getRoutes = routes => {
        return routes.map((prop, key) => {
            if (prop.layout === "/admin") {
                return (
                    <Route
                        path={prop.layout + prop.path}
                        render={props => (
                            <prop.component
                                {...props}
                                handleClick={this.handleNotificationClick}
                            />
                        )}
                        key={key}
                    />
                );
            } else {
                return null;
            }
        });
    };

    handleImageClick = image => {
        this.setState({image: image});
    };

    handleColorClick = color => {
        this.setState({color: color});
    };

    handleHasImage = hasImage => {
        this.setState({hasImage: hasImage});
    };

    handleFixedClick = () => {
        if (this.state.fixedClasses === "dropdown") {
            this.setState({fixedClasses: "dropdown show-dropdown open"});
        } else {
            this.setState({fixedClasses: "dropdown"});
        }
    };

    componentDidMount() {
        this.setState({_notificationSystem: this.refs.notificationSystem});
        var color = Math.floor(Math.random() * 4 + 1);
        var level;
        switch (color) {
            case 1:
                level = "success";
                break;
            case 2:
                level = "warning";
                break;
            case 3:
                level = "error";
                break;
            case 4:
                level = "info";
                break;
            default:
                break;
        }
    }

    componentDidUpdate(e) {
        if (
            window.innerWidth < 993 &&
            e.history.location.pathname !== e.location.pathname &&
            document.documentElement.className.indexOf("nav-open") !== -1
        ) {
            document.documentElement.classList.toggle("nav-open");
        }
        if (e.history.action === "PUSH") {
            document.documentElement.scrollTop = 0;
            document.scrollingElement.scrollTop = 0;
            this.refs.mainPanel.scrollTop = 0;
        }
    }

    getRoomName() {
        let url = window.location.href.split("/");
        return url[url.length - 1];
    }

    render() {
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Kfmn ' + localStorage.getItem('token'),
                'Authentication': 'Kfmn ' + localStorage.getItem('token')
            },
            body: ""
        };

        let _this = this;
        fetch(host + `/meeting/services/` + this.getRoomName(), requestOptions)
            .then(response => {
                console.log(response)
                _this.setState({
                    serviceName: "",
                    clientLogin: "",
                    employeeLogin: ""
                });
                return;
            });

        return (
            <div className="wrapper">
                <Sidebar {...this.props} routes={routes} image={this.state.image}
                         color={this.state.color}
                         hasImage={this.state.hasImage}/>
                <div id="main-panel" className="main-panel" ref="mainPanel">
                    <Jutsu
                        domain={"meet.jit.si"}
                        roomName={this.getRoomName()}
                        containerStyles={{width: '100%', height: '93%'}}
                    />
                    <Switch>{this.getRoutes(routes)}</Switch>
                    <Footer/>
                </div>
            </div>
        );
    }
}

export default JItsiConferece;
