import {SET_API_ENDPOINT} from '../actions/generalActions';

export function urlReducer(
    state = {apiEndpoint: 'http://localhost:8080'}, action) {
  switch (action.type) {
    case SET_API_ENDPOINT:
      return {...state, apiEndpoint: action.apiEndpoint};
    default:
      return {...state};
  }
}