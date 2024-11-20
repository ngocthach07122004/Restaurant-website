import React, { useState } from 'react'
import "./styles.scss"

const Signin = (props) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleLogin = () => {
    // You'll update this function later...
    console.log(email);
    console.log(password);
    const url = "http://127.0.0.1:8080/api/users/login"
    const payload = { email, password }
    fetch(url, {
      method: 'POST',
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(payload)
    })
    .then(res => res.json)
    .then(data => {console.log(data)})
    .catch(err => {console.log(err)});
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
      </div>
      <br />
      <div className={'inputContainer'}>
        <button className={'buttonContainer'} onClick={handleLogin}>Sign in</button>
      </div>
    </div>
  )
}

export default Signin