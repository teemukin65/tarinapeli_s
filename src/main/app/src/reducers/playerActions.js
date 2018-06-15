export const PLAYER_REGISTRATION_REQUEST = 'PLAYER_REGISTRATION_REQUEST'
export const PLAYER_REGISTRATION_SUCCESS = 'PLAYER_REGISTRATION_SUCCESS'
export const PLAYER_REGISTRATION_ERROR = 'PLAYER_REGISTRATION_ERROR'

export function registerPlayerRequest(profile) {
  return {
    type: PLAYER_REGISTRATION_REQUEST,
    payload: profile
  }
}

export const registerPlayerSuccess = (profile) => ({
  type: PLAYER_REGISTRATION_SUCCESS,
  payload: profile
})

export const registerPlayerError = (error) => ({
  type: PLAYER_REGISTRATION_ERROR,
  payload: error
})


