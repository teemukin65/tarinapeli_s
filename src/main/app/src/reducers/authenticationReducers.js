import {
  LOGIN_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  loginError,
  LOGOUT_SUCCESS,
  receiveLogin,
  receiveLogout,
  requestLogin,
  requestLogout,
  TOKEN_PREFIX
} from "./loginLogoutActions"

export function loginUser(creds) {

  let config = {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: {
      email: creds.email,
      password: creds.password

    }
  }

  return dispatch => {
    const NO_TOKEN_ERROR = 'No JWT Token header!'
    // We dispatch requestLogin to kickoff the call to the API
    dispatch(requestLogin(creds))


    return fetch('/api/games/players/login', config)
      .then(response =>
        response.json()
          .then(user => ({user, response})
          )
      ).then(({user, response}) => {
        if (!response.ok) {
          // If there was a problem, we want to
          // dispatch the error condition
          dispatch(loginError(user.message))
          return Promise.reject(user)
        } else {
          // If login was successful, set the token in local storage
          let authorizationHeader = response.headers.get('Authorization')
          if (typeof(authorizationHeader) !== 'string' || !authorizationHeader.startsWith(TOKEN_PREFIX, 0)) {
            dispatch(loginError(NO_TOKEN_ERROR))
            return Promise.reject(NO_TOKEN_ERROR)
          }
          localStorage.setItem('id_token', authorizationHeader.substring(TOKEN_PREFIX.length))
          // Dispatch the success action
          return dispatch(receiveLogin(user))
        }
      }).catch(err => console.log("Error: ", err))
  }
}


export function logoutUser() {
  return dispatch => {
    dispatch(requestLogout())
    localStorage.removeItem('id_token')
    dispatch(receiveLogout())
  }
}


export function auth(state = {
  errorMessage: null,
  isFetching: false,
  isAuthenticated: localStorage.getItem('id_token') ? true : false,
  user: null
}, action) {
  switch (action.type) {
    case LOGIN_REQUEST:
      return Object.assign({}, state, {
        isFetching: true,
        isAuthenticated: false,
        user: action.creds
      })
    case LOGIN_SUCCESS:
      return Object.assign({}, state, {
        isFetching: false,
        isAuthenticated: true,
        errorMessage: ''
      })
    case LOGIN_FAILURE:
      return Object.assign({}, state, {
        isFetching: false,
        isAuthenticated: false,
        errorMessage: action.message
      })
    case LOGOUT_SUCCESS:
      return Object.assign({}, state, {
        isFetching: true,
        isAuthenticated: false
      })
    default:
      return state
  }
}


