import React, {Component, PropTypes} from 'react'
import {Field, Form} from 'react-final-form'
import logo from './logo.svg'
import './App.css'

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

        <Form
          onSubmit={this.props.onRegisterPress}
          render={({handleSubmit, pristine, invalid}) => (
            <form onSubmit={handleSubmit}>

              <h2>Rekisteröidy tarinapelejä varten </h2>
              <div>
                <label>Taiteilijanimi</label>
                <Field name='nickName' component='input' placeholder='Unto Uneksija'/>
              </div>


              <div>
                <label>Sähköpostiosoite*</label>
                <Field name='email' component='input' placeholder='matti@virtanen.example.fi'/>
              </div>
              <div>
                <label>Salasana*</label>
                <Field name='password' component='input' type='password' placeholder='matti@virtanen.example.fi'/>
              </div>

              <button type='submit' disabled={pristine || invalid}>
                Submit
              </button>
            </form>

          )}
        />
      </div>
    );
  }
}


App.propTypes = {
  isAuthenticated: PropTypes.bool.isRequired,
  errorMessage: PropTypes.string
}


