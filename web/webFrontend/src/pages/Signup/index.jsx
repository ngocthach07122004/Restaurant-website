import React, { useState } from 'react'
// import { useNavigate } from 'react-router-dom'
import "./styles.scss"

const Signup = (props) => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [reEnterPassword, setReEnterPassword] = useState('')
  const [phone, setPhone] = useState('')
  const [firstname, setFirstname] = useState('')
  const [lastname, setLastname] = useState('')
  const [gender, setGender] = useState('')

  // const navigate = useNavigate()

  
  const handleSignup = () => {
    // You'll update this function later...
    console.log(email);
    console.log(password);
    const url = "http://127.0.0.1:8080/api/users/register"
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
    <div className={'container-fluid d-flex flex-column align-items-center'}>
      <div className={'titleContainer'}>
        <div>Sign up</div>
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={firstname}
          placeholder="First Name"
          onChange={(ev) => setFirstname(ev.target.value)}
          className={'inputBox'}
          type="text"
          required
        />
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={lastname}
          placeholder="Last Name"
          onChange={(ev) => setLastname(ev.target.value)}
          className={'inputBox'}
          type="text"
          required
        />
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={gender}
          placeholder="Gender"
          onChange={(ev) => setGender(ev.target.value)}
          className={'inputBox'}
          type="text"
          required
        />
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={phone}
          placeholder="Phone Number"
          onChange={(ev) => setPhone(ev.target.value)}
          className={'inputBox'}
          data-validate="{required:true, 'validate-number':true, 'validate-phoneLax': true}"
          type="text"
          required
        />
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={email}
          placeholder="Enter your email here"
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
          placeholder="Enter your password here"
          onChange={(ev) => setPassword(ev.target.value)}
          className={'inputBox'}
          type="password"
          required
        />
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={reEnterPassword}
          placeholder="Enter your password here"
          onChange={(ev) => setReEnterPassword(ev.target.value)}
          className={'inputBox'}
          type="password"
          required
        />
      </div>
      <br />
      <div className={'inputContainer'}>
        <button className={'buttonContainer'} onClick={handleSignup}>Sign up</button>
      </div>
    </div>
  )
}

export default Signup