import {auth} from "./authenticationReducers"
import {combineReducers} from 'redux'
import {playerRegistration} from "./playerReducers"

let reducers = combineReducers({
  auth,
  playerRegistration
})

export default reducers