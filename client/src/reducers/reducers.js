import {combineReducers} from 'redux';
import {loginReducer} from './loginReducer';
import {registerReducer} from './registerReducer';
import {menuReducer} from './menuReducer';
import {urlReducer} from './urlReducer';

// access different reducers in store by calling store with the appropriate
// reducer. e.g. store.loginReducer
export default combineReducers({
  loginReducer,
  registerReducer,
  menuReducer,
  urlReducer,
});
