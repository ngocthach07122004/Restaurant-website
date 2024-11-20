import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import "./styles.scss"

const Signin = (props) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [emailError, setEmailError] = useState('')
  const [passwordError, setPasswordError] = useState('')

  const navigate = useNavigate()

  const handleLogin = () => {
    // You'll update this function later...
    console.log(email);
    console.log(password);
  }

  return (
    <div className={'mainContainer'}>
      <div className={'titleContainer'}>
        <div>Login</div>
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={email}
          placeholder="Email"
          onChange={(ev) => setEmail(ev.target.value)}
          className={'inputBox'}
          type='email'
          required
        />
        <label className="errorLabel">{emailError}</label>
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={password}
          placeholder="Mật khẩu"
          onChange={(ev) => setPassword(ev.target.value)}
          className={'inputBox'}
          type="password"
          required
        />
        <label className="errorLabel">{passwordError}</label>
      </div>
      <br />
      <div className={'inputContainer'}>
        <button className={'buttonContainer'} onClick={handleLogin}>Sign in</button>
      </div>
    </div>
  )
}

export default Signin