import React from 'react';
import FindMeAppBar from '../containers/FindMeAppBar';
import MenuDrawer from '../containers/MenuDrawer';
import BodyLayout from './BodyLayout';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

const App = (props) => {
  return (
      <MuiThemeProvider>
        <div>
          <FindMeAppBar/>
          <MenuDrawer/>
          <BodyLayout>
            {props.children}
          </BodyLayout>
        </div>
      </MuiThemeProvider>
  );
};

export default App;
