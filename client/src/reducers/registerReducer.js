import {USER_REGISTERED} from '../actions/registerActions';

export function registerReducer(state = {registered: false}, action) {
  switch (action.type) {
    case USER_REGISTERED:
      return {...state, registered: action.registered};
    default:
      return {...state};
  }
}
