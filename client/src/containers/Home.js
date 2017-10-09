import React, {Component} from 'react';
import {connect} from 'react-redux';

@connect((store) => {
  return {
    apiEndpoint: store.urlReducer.apiEndpoint,
    authToken: store.loginReducer.authToken,
  };
})
class HomeContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
    };
  }

  render() {
    return (
        <div><p> hello there, {this.props.authToken} </p></div>
    );
  }
}

export default HomeContainer;
