import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React</h2>
        </div>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
          <form action="http://localhost:9080/signin/facebook" method="post">
              <h1>Please login</h1>
              <button type="submit">Login</button>
          </form>
      </div>
    );
  }
}

export default App;
