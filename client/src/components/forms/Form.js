import React from 'react';
import Paper from 'material-ui/Paper';

const style = {
  display: 'inline-block',
  minWidth: '300px',
  marginTop: '10px',

};

const Form = (props) => {
  return (
      <div>
        <Paper style={style} zDepth={2}>
          {props.children}
        </Paper>
      </div>

  );
};

export default Form;