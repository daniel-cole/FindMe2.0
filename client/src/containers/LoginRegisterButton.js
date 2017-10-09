import React, {Component} from 'react';
import FlatButton from 'material-ui/FlatButton';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';
// STYLE HERE HAS BEEN OVERRIDDEN - ANY CHANGES TO DEFAULT WILL NOT AFFECT THIS
// YES IT'S TERRIBLE, I KNOW
const style = {color: '#ffffff', marginTop: 7};

@connect((store) => {
  return {
    logged: store.loginReducer.logged,
  };
})
class LoginRegisterButton extends Component {
  constructor(props) {
    super(props);
  }

  renderLogin() {
    this.props.history.push('/login');

  }

  renderRegister() {
    this.props.history.push('/register');
  }

  render() {
    return (
        <div>
          <FlatButton style={style} label="Login"
                      onClick={() => this.renderLogin()}/>
          <FlatButton style={style} label="Register"
                      onClick={() => this.renderRegister()}/>
        </div>
    );
  }
}

export default withRouter(LoginRegisterButton);
