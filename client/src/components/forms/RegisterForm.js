import React from 'react';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';

const buttonStyle = {
  margin: 12,
};

const RegisterForm = (props) => {
  return (
      <div>
        <h3>Register a new FindMe account</h3>
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
        <TextField
            type="email"
            hintText="Enter your Email"
            floatingLabelText="Email"
            onChange={
              (event, newValue) => props.updateState({email: newValue})
            }
        />
        <br/>
        <RaisedButton label="Register" style={buttonStyle}
                      primary={true} onClick={
          () => props.register()
        }/>
      </div>


  );
};

export default RegisterForm;