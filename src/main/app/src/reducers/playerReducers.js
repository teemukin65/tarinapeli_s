import {
  PLAYER_REGISTRATION_ERROR,
  PLAYER_REGISTRATION_REQUEST,
  PLAYER_REGISTRATION_SUCCESS,
  registerPlayerError,
  registerPlayerRequest,
  registerPlayerSuccess
} from "./playerActions"


export const registerPlayer = (values) => (dispatch) => {
  const {email, password, nickName} = values
  const requestedProfile = {
    'email': email,
    'password': password,
    'nickName': nickName
  }
  var jsonHeaders = new Headers()
  jsonHeaders.append('Content-Type', 'application/json')
  jsonHeaders.append('Accept', 'application/json')
  const registerInit = {
    method: 'POST',
    headers: jsonHeaders,
    body: JSON.stringify(requestedProfile)
  }
  dispatch(registerPlayerRequest(requestedProfile))

  return fetch('/api/games/players/sign-up', registerInit)
    .then((response) => {
      if (response.ok) {
        return response.json()
          .then((json) => {
            return dispatch(registerPlayerSuccess(json))
          })
      } else {
        dispatch(registerPlayerError(response.statusText))
        throw new Error(response.error().toString())
      }
    })
    .catch((error) => {
        throw new Error(error)
      }
    )


}


export const playerRegistration = (state = {
  errorMessage: null,
  isFetching: false,
  profile: null
}, action) => {

  switch (action.type) {
    case PLAYER_REGISTRATION_REQUEST:
      return Object.assign({}, state, {
        errorMessage: null,
        isFetching: true,
        profile: null
      })
    case PLAYER_REGISTRATION_SUCCESS:
      return Object.assign({}, state, {
        errorMessage: null,
        isFetching: false,
        profile: action.payload
      })
    case PLAYER_REGISTRATION_ERROR:
      return Object.assign({}, state, {
        errorMessage: action.payload,
        isFetching: false
      })
    default:
      return state
  }
}






