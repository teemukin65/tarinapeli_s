import React from 'react'
import {Field, Form} from 'react-final-form'


class RegisterPlayerForm extends React.Component {

  render() {
    return (
      <Form
        onSubmit={this.props.onSubmit}
        render={({handleSubmit, form, submitting, pristine, invalid}) => (

          <form onSubmit={handleSubmit}>

            <h2>Rekisteröidy tarinapelejä varten </h2>
            <div>
              <label> Taiteilijanimi </label>
              <Field name='nickName' component='input' placeholder='Unto Uneksija'/>
            </div>


            <div>
              <label>Sähköpostiosoite*</label>
              <Field name='email' component='input' placeholder='matti.virtanen@example.fi'/>
            </div>
            <div>
              <label> Salasana *
              </label>
              <Field
                name='password'
                component='input'
                type='password'
                placeholder='Yli 8 Merkkiä Pitkä SalAsana'/>
            </div>

            <button type='submit' disabled={pristine || invalid}>
              Lähetä
            </button>
          </form>
        )}
      />
    )
  }
}


export default RegisterPlayerForm
