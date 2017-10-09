import {USER_LOGGED_IN} from '../actions/loginActions';

export function loginReducer(state = {loggedIn: false, authToken: ''}, action) {
  switch (action.type) {
    case USER_LOGGED_IN:
      console.log(action.authToken);
      return {
        ...state,
        loggedIn: action.payload.loggedIn,
        authToken: action.payload.authToken,
      };
    default:
      return {...state};
  }
}
