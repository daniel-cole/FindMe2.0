import fetch from 'isomorphic-fetch';
import axios from 'axios';

export const USER_LOGGED_IN = 'USER_LOGGED_IN';

function setUserLoggedIn(loggedIn, authToken) {
  console.log(authToken);
  return {
    type: USER_LOGGED_IN, payload: {
      loggedIn: loggedIn,
      authToken: authToken,
    },
  };
}

export function loginUser(username, password, apiEndpoint) {
  return (dispatch) => {
    return axios.post(apiEndpoint + '/login', {
        username: username,
        password: password,
    }).then(function(response) {
      console.log(response.headers.authorization);
      if(response.headers.authorization === undefined){
        dispatch(setUserLoggedIn(false), '');
      }
      dispatch(setUserLoggedIn(true, response.headers.authorization));
    }).catch(function(error) {
      console.log(error);
    });
    // return fetch(apiEndpoint + '/login', {
    //   method: 'POST',
    //   body: JSON.stringify({
    //     username: username,
    //     password: password,
    //   }),
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    // }).then(
    //     (response) => {
    //       console.log(response);
    //       if (response.status === 200) {
    //         console.log('we gucci');
    //         console.log('HEADERS!');
    //         console.log(response);
    //         dispatch(setUserLoggedIn(true));
    //       }
    //       else {
    //         console.log('failed to login');
    //       }
    //     },
    // );
  };
}