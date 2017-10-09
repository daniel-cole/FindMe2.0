import React, {Component} from 'react';
import Drawer from 'material-ui/Drawer';
import MenuItem from 'material-ui/MenuItem';
import {connect} from 'react-redux';
import {toggleDrawer} from '../actions/generalActions';
import AppBar from 'material-ui/AppBar';
import NavigationClose from 'material-ui/svg-icons/navigation/close';
import IconButton from 'material-ui/IconButton';

@connect((store) => {
  return {
    drawerOpen: store.menuReducer.drawerOpen,
  };
})
class MenuDrawer extends Component {
  constructor(props) {
    super(props);

  }

  closeDrawer() {
    this.props.dispatch(toggleDrawer(false));
  }

  navigateTo(){

  }

  render() {
    return (
        <div>
          <Drawer
              docked={false}
              width={200}
              open={this.props.drawerOpen}
              onRequestChange={() => this.closeDrawer()}
          >
            <AppBar
                title="Menu"
                showMenuIconButton={false}
                iconElementRight={<IconButton><NavigationClose
                    onClick={() => this.closeDrawer()}/></IconButton>}
            />
            <MenuItem onClick={this.handleClose}>Lobbies</MenuItem>
            <MenuItem onClick={this.handleClose}>Friends</MenuItem>
            <MenuItem onClick={this.handleClose}>Settings</MenuItem>
            <MenuItem onClick={this.handleClose}>Logout</MenuItem>

          </Drawer>
        </div>
    );
  }
}

export default MenuDrawer;
