import React, {Component} from 'react'
import logo from './logo.svg'
import './App.css'
import RegisterPlayerForm from "./components/registerPlayerForm"

export class App extends Component {
  render() {
    return (
      <div className='App'>
        <div className='App-header'>
          <img src={logo} className='App-logo' alt='logo'/>
          <h1>Tarinapeli</h1>
        </div>
        <h2>Aloita tarinapeli</h2>
        <h2>Tarinat odottamassa sinua</h2>
        <h2>Tarinoidut tarinat </h2>

        <h2>Liity mukaan</h2>

        Tee tunnus, niin voit päästä mukaan pelaamaan..

        <RegisterPlayerForm onSubmit={this.props.onRegisterPress}/>

      </div>
    );
  }
}


App.propTypes = {
  isAuthenticated: React.PropTypes.bool.isRequired,
  errorMessage: React.PropTypes.string,

}


