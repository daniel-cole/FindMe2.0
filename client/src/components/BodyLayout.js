import React from 'react';

const style = {
  'textAlign': 'center',
};

const BodyLayout = (props) => (
    <div style={style}>
      {props.children}
    </div>
);

export default BodyLayout;
