import React, {Component} from 'react';
import AppBar from 'material-ui/AppBar';
import IconButton from 'material-ui/IconButton';
import NavigationMenu from 'material-ui/svg-icons/navigation/menu';
import LoginRegisterButton from './LoginRegisterButton';
import Logged from '../components/Logged';
import {connect} from 'react-redux';
import {toggleDrawer} from '../actions/generalActions';
import {withRouter} from 'react-router-dom';

@connect((store) => {
  return {
    loggedIn: store.loginReducer.loggedIn,
  };
})
class FindMeAppBar extends Component {
  constructor(props) {
    super(props);
  }

  openDrawer() {
    this.props.dispatch(toggleDrawer(true));
  }

  render() {
    return (
        <div>
          <AppBar
              title="FindMe"
              showMenuIconButton={this.props.loggedIn}
              iconElementLeft={
                <IconButton>
                  <NavigationMenu
                      onClick={() => this.openDrawer()}
                  />
                </IconButton>}
              iconElementRight={this.props.loggedIn ? <Logged/> :
                  <LoginRegisterButton {...this.props} />}
          />
        </div>
    );
  }
}

export default withRouter(FindMeAppBar);
