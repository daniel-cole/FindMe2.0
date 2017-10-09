import {SET_DRAWER_OPEN} from '../actions/generalActions';

export function menuReducer(state = {drawerOpen: false}, action) {
  switch (action.type) {
    case SET_DRAWER_OPEN:
      return {...state, drawerOpen: action.drawerOpen};
    default:
      return {...state};
  }
}
