import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
// import { useNavigate } from 'react-router-dom'
import "./styles.scss";
import Validator from "../../Validate/validator";

const Signup = (props) => {
  const [CCCD, setCCCD] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [reEnterPassword, setReEnterPassword] = useState("");
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  // const [phoneList, setPhoneList] = useState([]);
  const [gender, setGender] = useState("");
  const [birthdate, setBirthdate] = useState(
    new Date().toISOString().split("T")[0]
  );

  // validate
  const [checkEmail, setCheckEmail] = useState(true);

  const current = new Date().toISOString().split("T")[0];

  const emailElement = useRef();
  const errorEmailElement = useRef();

  // const navigate = useNavigate()
  const navigate = useNavigate();

  const handleSignup = () => {
    console.log("CHECK");
    // You'll update this function later...
    const url = "http://localhost:8080/thongTin/create";
    let handlerListPhone = [phone];

    const payload = {
      cccd: CCCD,
      tenDangNhap: username,
      matKhau: password,
      ho: lastname,
      ten: firstname,
      ngaySinh: birthdate,
      email: email,
      gioiTinh: gender,
      soDienThoai: handlerListPhone,
      maTaiKhoan: "",
      cccdQuanTriVien: "",
      anhThongTin: "https://www.vecteezy.com/free-vector/user-profile",
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
        localStorage.setItem("userdata", JSON.stringify(data));
        localStorage.setItem("isLogin", true);
        navigate("/menu");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const functionCheckEmail = (valueText) => {
    var keyCheck = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    //    var textValue = document.querySelector(idInput);
    return keyCheck.test(valueText) ? true : false;
  };

  const handlerEmail = (res) => {
    // let newEmail = ;
    setEmail(res.target.value);
    let newCheckEmail = functionCheckEmail(res.target.value);
    // setCheckEmail(newCheckEmail);
    if (newCheckEmail) {
      emailElement.current.style.cssText = `
      border-color: grey;
     
    `;
      errorEmailElement.current.style.cssText = `
    display:none; 

`;
    } else {
      emailElement.current.style.cssText = `
      border-color: red;
     
    `;
      errorEmailElement.current.style.cssText = `
       display:block; 
   
  `;
    }
  };
  return (
    <>
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
            onChange={(ev) => {
              setPhone(ev.target.value);
              // setPhoneList((prev) => {

              //   return [...prev, ];
              // })
            }}
            className={"inputBox"}
            data-validate="{required:true, 'validate-number':true, 'validate-phoneLax': true}"
            type="text"
            required
          />
        </div>
        <br />
        <div className={"inputContainer"}>
          <input
            ref={emailElement}
            id={"password"}
            value={email}
            placeholder="Địa chỉ email"
            onChange={(res) => handlerEmail(res)}
            className={"inputBox"}
            type="email"
            required
          />
          <div className={"error_element_css_style"} ref={errorEmailElement}>
            Vui lòng nhập đúng định dạng gmail
          </div>
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
    </>
  );
  // return (
  //   <>
  //     <div className="mainContainer_alert"></div>
  //     <div className="bodyElement">
  //       <form id="container__form-1">
  //         <div id="form-1" className="container">
  //           <div className="container__element --title">
  //             <div className="container__element_infoChild --firstChild">
  //               Thành viên đăng ký
  //             </div>
  //             <div className="container__element_infoChild --secondChild">
  //               Cùng nhau học lập trình miễn phí tại F8
  //             </div>
  //           </div>
  //           <div className="container__element --inputBox">
  //             <label htmlFor="fullName">Full name</label>
  //             <br />

  //             <input
  //               name="fullName"
  //               className="container__element__inputBox"
  //               id="fullName"
  //               placeholder="Ngọc Thạch"
  //             />
  //             <br />

  //             <span className="container__element__error"></span>
  //           </div>
  //           <div className="container__element --inputBox">
  //             <label htmlFor="email">Email</label>
  //             <br />
  //             <input
  //               name="email"
  //               className="container__element__inputBox"
  //               id="email"
  //               placeholder="email@domain.com"
  //             />
  //             <br />
  //             <span className="container__element__error"></span>
  //           </div>
  //           <div className="container__element --inputBox">
  //             <label htmlFor="password">Password</label>
  //             <br />
  //             <input
  //               name="password"
  //               type="password"
  //               className="container__element__inputBox"
  //               id="password"
  //               placeholder="Nhập mật khẩu"
  //             />
  //             <br />
  //             <span className="container__element__error"></span>
  //           </div>
  //           <div className="container__element --inputBox">
  //             <label htmlFor="confirmPassword">Confirm password</label>
  //             <br />
  //             <input
  //               name="confirmPassword"
  //               type="password"
  //               className="container__element__inputBox"
  //               id="confirmPassword"
  //               placeholder="Nhập lại mật khẩu"
  //             />
  //             <br />
  //             <span className="container__element__error"></span>
  //           </div>
  //           <div className="container__element">
  //             <div className="container__element__RadioChild --inputRadio --optionsBox">
  //               <label htmlFor="Male">Male</label>
  //               <input
  //                 name="gender"
  //                 type="radio"
  //                 className="container__element__inputBox"
  //                 value="male"
  //               />
  //               <label htmlFor="Female">Female</label>
  //               <input
  //                 name="gender"
  //                 type="radio"
  //                 className="container__element__inputBox"
  //                 value="female"
  //               />
  //               <label htmlFor="Other">Other</label>
  //               <input
  //                 name="gender"
  //                 type="radio"
  //                 className="container__element__inputBox"
  //                 value="other"
  //               />
  //             </div>
  //             <span className="container__element__error"></span>
  //           </div>
  //           <div className="container__element --inputBox --typeSelect --optionsBox">
  //             <div className="container__elementSelectChild">
  //               <label htmlFor="Province">Province</label>

  //               <select name="province" id="province" className="--inputSelect">
  //                 <option value="">--Province--</option>
  //                 <option value="hn">Ha Noi</option>
  //                 <option value="hcm">Ho Chi Minh</option>
  //               </select>
  //             </div>
  //             <span className="container__element__error"></span>
  //           </div>
  //           <div className="container__element">
  //             <div className="container__elementCheckBoxChild --inputCheckBox --optionsBox">
  //               <label htmlFor="student">Student</label>
  //               <input
  //                 name="job"
  //                 type="checkbox"
  //                 className="container__element__inputCheckBox"
  //                 value="student"
  //               />
  //               <label htmlFor="work">Work</label>
  //               <input
  //                 name="job"
  //                 type="checkbox"
  //                 className="container__element__inputCheckBox"
  //                 value="work"
  //               />
  //               <label htmlFor="both">Both</label>
  //               <input
  //                 name="job"
  //                 type="checkbox"
  //                 className="container__element__inputCheckBox"
  //                 value="both"
  //               />
  //             </div>

  //             <span className="container__element__error"></span>
  //           </div>
  //           <button type="submit" className="container__element --button">
  //             Đăng kí
  //           </button>
  //         </div>
  //       </form>
  //     </div>
  //     {Validator({
  //       form: "#form-1",
  //       formContainer: "#container__form-1",
  //       validDisplayText: "--valid",
  //       validDisplayBox: "--validBox",
  //       classNameDisplayRegister: ".mainContainer_alert",
  //       mainBoxCss: "mainContainer__alertStage",
  //       parentSelector: ".container__element",
  //       setVaildCssOptionsBox: ".--optionsBox",
  //       rules: [
  //         Validator.isRequired("#fullName"),
  //         Validator.isRequired("#email"),
  //         Validator.isEmail("#email"),
  //         Validator.isRequiredPassword("#password"),
  //         Validator.isRequired("#confirmPassword"),
  //         Validator.isConfirmPassword("#confirmPassword", "#password"),
  //         Validator.isRequiredBox("radio"),
  //         Validator.isRequired("#province"),
  //         Validator.isRequiredBox("checkbox"),
  //       ],
  //       getRespone: function (data) {
  //         console.log(data);
  //       },
  //     })}
  //   </>
  // );

  // return (
  //   <>
  //     <form id={"container__form-1"}>
  //       <div id="form-1" className="container">
  //         <div
  //           className={"container-fluid d-flex flex-column align-items-center"}
  //         >
  //           <div className={"titleContainer"}>
  //             <div>Sign up</div>
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               value={CCCD}
  //               placeholder="CCCD"
  //               onChange={(ev) => setCCCD(ev.target.value)}
  //               className={"inputBox"}
  //               type="text"
  //               required
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               value={username}
  //               placeholder="Tên đăng nhập"
  //               onChange={(ev) => setUsername(ev.target.value)}
  //               className={"inputBox"}
  //               type="text"
  //               required
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               value={firstname}
  //               placeholder="Tên (*Anh)"
  //               onChange={(ev) => setFirstname(ev.target.value)}
  //               className={"inputBox"}
  //               type="text"
  //               required
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               value={lastname}
  //               placeholder="Họ và tên đệm (*Nguyễn Văn)"
  //               onChange={(ev) => setLastname(ev.target.value)}
  //               className={"inputBox"}
  //               type="text"
  //               required
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <select
  //               value={gender}
  //               onChange={(ev) => setGender(ev.target.value)}
  //               className="inputBox"
  //               required
  //             >
  //               <option value="M">Male</option>
  //               <option value="F">Female</option>
  //               <option value="O">Other</option>
  //             </select>
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               value={phone}
  //               placeholder="Số điện thoại"
  //               onChange={(ev) => {
  //                 setPhone(ev.target.value);
  //                 // setPhoneList((prev) => {

  //                 //   return [...prev, ];
  //                 // })
  //               }}
  //               className={"inputBox"}
  //               data-validate="{required:true, 'validate-number':true, 'validate-phoneLax': true}"
  //               type="text"
  //               required
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               id={"password"}
  //               value={email}
  //               placeholder="Địa chỉ email"
  //               onChange={(ev) => setEmail(ev.target.value)}
  //               className={"inputBox"}
  //               type="email"
  //               required
  //             />
  //             <span class="container__element__error"></span>
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               type="date"
  //               placeholder="Enter BirthDate"
  //               value={birthdate}
  //               onChange={(ev) => setBirthdate(ev.target.value)}
  //               name="birthdate"
  //               max={current}
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               value={password}
  //               placeholder="Mật khẩu"
  //               onChange={(ev) => setPassword(ev.target.value)}
  //               className={"inputBox"}
  //               type="password"
  //               required
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <input
  //               value={reEnterPassword}
  //               placeholder="Xác nhận mật khẩu"
  //               onChange={(ev) => setReEnterPassword(ev.target.value)}
  //               className={"inputBox"}
  //               type="password"
  //               required
  //             />
  //           </div>
  //           <br />
  //           <div className={"inputContainer"}>
  //             <button className={"buttonContainer"} onClick={handleSignup}>
  //               Sign up
  //             </button>
  //           </div>
  //         </div>
  //       </div>
  //     </form>
  //     {/* {Validator({
  //       form: "#form-1",
  //       formContainer: "#container__form-1",
  //       validDisplayText: "--valid",
  //       validDisplayBox: "--validBox",
  //       classNameDisplayRegister: ".mainContainer_alert",
  //       mainBoxCss: "mainContainer__alertStage",
  //       parentSelector: ".container__element",
  //       setVaildCssOptionsBox: ".--optionsBox",
  //       rules: [
  //         // Validator.isRequired("#fullName"),
  //         // Validator.isRequired("#email"),
  //         Validator.isEmail("#email"),
  //         // Validator.isRequiredPassword("#password"),
  //         // Validator.isRequired("#confirmPassword"),
  //         // Validator.isConfirmPassword("#confirmPassword", "#password"),
  //         // Validator.isRequiredBox("radio"),
  //         // Validator.isRequired("#province"),
  //         // Validator.isRequiredBox("checkbox"),
  //       ],
  //       getRespone: function (data) {
  //         console.log(data);
  //       },
  //     })} */}
  //   </>
  // );
};

export default Signup;
