import React from 'react'
import ReactDOM from 'react-dom'
import {applyMiddleware, createStore} from 'redux'
import {Provider} from 'react-redux'
import AppContainer from './AppContainer'
import {default as reducers} from './reducers'
import './index.css'
import thunkMiddleware from 'redux-thunk'

let createStoreWithMiddleware = applyMiddleware(thunkMiddleware)(createStore)
let store = createStoreWithMiddleware(reducers)

ReactDOM.render(
  <Provider store={store}>
    <AppContainer/>
  </Provider>,
  document.getElementById('root')
);
