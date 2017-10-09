import React from 'react';
import Route from 'react-router-dom/Route';
import Switch from 'react-router-dom/Switch';
import Router from 'react-router-dom/Router';
import createBrowserHistory from 'history/createBrowserHistory';

import App from '../components/App';
import Login from '../containers/Login';
import Home from '../containers/Home';
import Greeting from '../components/Greeting';
import NotFound from '../components/NotFound';
import Lobbies from '../components/Lobbies';
import Friends from '../components/Friends';
import Settings from '../components/Settings';
import Register from '../containers/Register';

const browserHistory = createBrowserHistory();

const Routes = () => (
    <Router history={browserHistory}>
      <div>
        <App>
          <Switch>
            <Route exact path="/" component={Greeting}/>
            <Route path="/home" component={Home}/>
            <Route path="/login" component={Login}/>
            <Route path="/register" component={Register}/>
            <Route path="/settings" component={Settings}/>
            <Route path="/ lobbies" component={Lobbies}/>
            <Route path="/friends" component={Friends}/>
            <Route component={NotFound}/>
          </Switch>
        </App>
      </div>
    </Router>
);

export default Routes;
