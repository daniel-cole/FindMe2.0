import React, {Component} from 'react';
import {connect} from 'react-redux';
import Form from '../components/forms/Form';
import LoginForm from '../components/forms/LoginForm';
import {loginUser} from '../actions/loginActions';
import {withRouter} from 'react-router-dom';

@connect((store) => {
  return {
    apiEndpoint: store.urlReducer.apiEndpoint,
    loggedIn: store.loginReducer.loggedIn,
  };
})
class LoginContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
    };
  }

  componentWillReceiveProps(nextProps){
    if(nextProps.loggedIn){
        this.props.history.push('/home');
    }
  }

  updateState(state) {
    this.setState(state);
  }

  login() {
    this.props.dispatch(loginUser(this.state.username, this.state.password, this.props.apiEndpoint));
  }

  render() {
    return (
        <div>
          <Form>
            <LoginForm login={this.login.bind(this)}
                       updateState={this.updateState.bind(this)}/>
          </Form>
        </div>
    );
  }
}

export default withRouter(LoginContainer);
