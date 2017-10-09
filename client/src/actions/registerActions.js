import fetch from 'isomorphic-fetch';

export const USER_REGISTERED = 'USER_REGISTERED';

export function setUserRegistered(registered) {
  return {type: USER_REGISTERED, registered: registered};
}

export function registerUser(username, password, email, apiEndpoint) {
  return (dispatch) => {
    return fetch(apiEndpoint + '/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: username,
        password: password,
        email: email,
      }),
    }).then(
        (response) => {
          console.log(response);
          if (response.status === 201) {
            console.log('we gucci, user registered!');
            dispatch(setUserRegistered(true));
          }
          else {
            console.log('failed to register');
          }
        },
    );
  };
}