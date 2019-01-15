import React, { Component } from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import './App.css';
import Postline from './pages/Postline';

class App extends Component { 
  render() {
    return (
      <BrowserRouter> 
        <Switch>
          <Route path="" component={Postline} /> 
        </Switch> 
      </BrowserRouter>
    );
  }
}

export default App;
