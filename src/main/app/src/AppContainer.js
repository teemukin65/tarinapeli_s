import {connect} from 'react-redux'
import {App} from './App'
import {loginUser} from "./reducers/authenticationReducers"
import {registerPlayer} from "./reducers/playerReducers"


const mapStateToProps = (state, ownProps) => ({
  isAuthenticated: state.auth.isAuthenticated,
  user: state.playerRegistration.profile
})


const mapDispatchToProps = (dispatch, ownProprops) => {
  return {
    'onRegisterPress': (info) => {
      let registrationInfo = {
        email: info.email,
        password: info.password,
        nickName: info.nickName
      }
      return dispatch(registerPlayer(registrationInfo))
    },
    'onLoginPress': creds => {
      let authentication = {
        email: creds.email,
        password: creds.password
      }
      return dispatch(loginUser(creds))
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(App)