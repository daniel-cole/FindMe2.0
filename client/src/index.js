import React from 'react';
import {Provider} from 'react-redux';
import ReactDOM from 'react-dom';
import thunkMiddleware from 'redux-thunk';
import {createLogger} from 'redux-logger';

import Routes from './routes/routes.js';

import {createStore, applyMiddleware} from 'redux';
import reducers from './reducers/reducers.js';

import registerServiceWorker from './registerServiceWorker';

import 'pace-progress';
import './styles/styles.scss';

let apiEndpoint = 'http://localhost:8080/api';

/* eslint-disable no-process-env */
if (process.env.NODE_ENV == 'production') {
  apiEndpoint = 'https://www.thekingwizard.com/api';
}

const loggerMiddleware = createLogger();

const initialState = {urlReducer: {apiEndpoint: apiEndpoint}};
let store = createStore(reducers, initialState, applyMiddleware(
    thunkMiddleware,
    loggerMiddleware,
));

ReactDOM.render(
    <Provider store={store}>
      <Routes/>
    </Provider>, document.getElementById('root'));
registerServiceWorker();
