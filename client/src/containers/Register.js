import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import {connect} from 'react-redux';
import {withRouter} from 'react-router-dom';
import RegisterForm from '../components/forms/RegisterForm';
import Form from '../components/forms/Form';
import {registerUser} from '../actions/registerActions';
import {setUserRegistered} from '../actions/registerActions';

const buttonStyle = {
  margin: 12,
};

@connect((store) => {
  return {
    registered: store.registerReducer.registered,
    apiEndpoint: store.urlReducer.apiEndpoint,
  };
})
class Register extends Component {
  constructor(props) {
    super(props);

    this.state = {
      username: '',
      password: '',
      email: '',
    };
  }

  updateState(state) {
    this.setState(state);
  }

  register() {
    this.props.dispatch(registerUser(this.state.username, this.state.password, this.state.email,
        this.props.apiEndpoint));
  }

  render() {
    return (
        <div>
          <Form>
            {this.props.registered ? <div><h3> Successfully Registered! </h3>
                  <RaisedButton label='Click to Login' style={buttonStyle}
                                primary={true}
                                onClick={() => {
                                  this.props.history.push('/login');
                                  this.props.dispatch(setUserRegistered(false));
                                }}/>
                </div>
                : <RegisterForm register={this.register.bind(this)}
                                updateState={this.updateState.bind(this)}
                />
            }
          </Form>
        </div>
    );
  }
}

export default withRouter(Register);
