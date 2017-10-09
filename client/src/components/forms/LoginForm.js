import React from 'react';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';

const buttonStyle = {
  margin: 12,
};

const LoginForm = (props) => {
  return (
      <div>
        <h3>Login to your FindMe account</h3>
        <TextField
            hintText="Enter your Username"
            floatingLabelText="Username"
            onChange={
              (event, newValue) => props.updateState({username: newValue})
            }
        />
        <br/>
        <TextField
            type="password"
            hintText="Enter your Password"
            floatingLabelText="Password"
            onChange={
              (event, newValue) => props.updateState({password: newValue})
            }
        />
        <br/>
        <RaisedButton label="Login" primary={true} style={buttonStyle} onClick={
          () => props.login()
        }/>
      </div>
  );
};

export default LoginForm;