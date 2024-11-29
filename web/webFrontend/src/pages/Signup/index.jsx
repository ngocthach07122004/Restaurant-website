import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
// import { useNavigate } from 'react-router-dom'
import "./styles.scss";

const Signup = (props) => {
  const [CCCD, setCCCD] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [reEnterPassword, setReEnterPassword] = useState("");
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [gender, setGender] = useState("");
  const [birthdate, setBirthdate] = useState(
    new Date().toISOString().split("T")[0]
  );
  const current = new Date().toISOString().split("T")[0];

  // const navigate = useNavigate()
  const navigate = useNavigate();

  const handleSignup = () => {
    // You'll update this function later...
    const url = "http://localhost:8080/thongTin/create";
    const payload = {
      "cccd": CCCD,
      "tenDangNhap": username,
      "matKhau": password,
      "ho": lastname,
      "ten": firstname,
      "ngaySinh": birthdate,
      "email": email,
      "gioiTinh": gender,
      "soDienThoai": phone,
      "maTaiKhoan": "",
      "cccdQuanTriVien": "",
      "anhThongTin": "https://www.vecteezy.com/free-vector/user-profile",
    };
    console.log(payload);
    fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        localStorage.setItem('userdata', JSON.stringify(data));
        localStorage.setItem('isLogin', true);
        navigate('/menu')
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className={"container-fluid d-flex flex-column align-items-center"}>
      <div className={"titleContainer"}>
        <div>Sign up</div>
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={CCCD}
          placeholder="CCCD"
          onChange={(ev) => setCCCD(ev.target.value)}
          className={"inputBox"}
          type="text"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={username}
          placeholder="Tên đăng nhập"
          onChange={(ev) => setUsername(ev.target.value)}
          className={"inputBox"}
          type="text"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={firstname}
          placeholder="Tên (*Anh)"
          onChange={(ev) => setFirstname(ev.target.value)}
          className={"inputBox"}
          type="text"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={lastname}
          placeholder="Họ và tên đệm (*Nguyễn Văn)"
          onChange={(ev) => setLastname(ev.target.value)}
          className={"inputBox"}
          type="text"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <select
          value={gender}
          onChange={(ev) => setGender(ev.target.value)}
          className="inputBox"
          required
        >
          <option value="M">Male</option>
          <option value="F">Female</option>
          <option value="O">Other</option>
        </select>
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={phone}
          placeholder="Số điện thoại"
          onChange={(ev) => setPhone(ev.target.value)}
          className={"inputBox"}
          data-validate="{required:true, 'validate-number':true, 'validate-phoneLax': true}"
          type="text"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={email}
          placeholder="Địa chỉ email"
          onChange={(ev) => setEmail(ev.target.value)}
          className={"inputBox"}
          type="email"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          type="date"
          placeholder="Enter BirthDate"
          value={birthdate}
          onChange={(ev) => setBirthdate(ev.target.value)}
          name="birthdate"
          max={current}
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={password}
          placeholder="Mật khẩu"
          onChange={(ev) => setPassword(ev.target.value)}
          className={"inputBox"}
          type="password"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <input
          value={reEnterPassword}
          placeholder="Xác nhận mật khẩu"
          onChange={(ev) => setReEnterPassword(ev.target.value)}
          className={"inputBox"}
          type="password"
          required
        />
      </div>
      <br />
      <div className={"inputContainer"}>
        <button className={"buttonContainer"} onClick={handleSignup}>
          Sign up
        </button>
      </div>
    </div>
  );
};

export default Signup;
