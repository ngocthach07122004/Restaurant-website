import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './styles.scss';

const Recruit = () => {
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

  const [branches, setBranches] = useState([]);
  const [formData, setFormData] = useState({
    branch: '',
    cccd: '',
    anhThongTin: 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png',
    email: '',
    gender: '',
    firstname: '',
    password: '',
    birthdate: new Date().toISOString().split("T")[0],
    lastname: '',
    username: '',
    cccdQuanTriVien: '999999999',
    phone: '',
    resume: null
  });

  const [validated, setValidated] = useState(false);
  const location = useLocation();
  const navigate = useNavigate();
  const { branchId, branchName } = location.state || {};

  useEffect(() => {
    axios.get('http://localhost:8080/chinhanh/all')
      .then(response => {
        setBranches(response.data);
      })
      .catch(error => {
        console.error('Error fetching branch data:', error);
      });
  }, []);

  const handleInputChange = (e) => {
    const { name, value, files } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: files ? files[0] : value
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const form = event.currentTarget;

    if (form.checkValidity() === false) {
      event.stopPropagation();
    } else {
      const submitData = new FormData();
      Object.keys(formData).forEach(key => {
        if (formData[key]) {
          submitData.append(key, formData[key]);
        }
      });

      axios.post('http://localhost:8080/recruit', submitData)
        .then(response => {
          console.log('Form submitted successfully:', response.data);
          navigate('/success');
        })
        .catch(error => {
          console.error('Error submitting form:', error);
        });
    }

    setValidated(true);
  };

  return (
    <div className="recruit-page">
      <div className="cardRecruit">
        <img src="https://scontent.fsgn21-1.fna.fbcdn.net/v/t39.30808-6/432759400_938494181612417_8512233600984010341_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=127cfc&_nc_ohc=O0TJOH-TqCEQ7kNvgH8ShLw&_nc_zt=23&_nc_ht=scontent.fsgn21-1.fna&_nc_gid=A5dybqVC5_8_xPQ3IyLQKZq&oh=00_AYBKOHi8gQHYWMZ5UaMXJADwSo-dOGqtwqIIdc390dTXAQ&oe=675B3C45"
          className="w-100"
          alt=""
        />
        <h1>Come join us</h1>
        <form
          onSubmit={handleSubmit}
          noValidate
          className={`needs-validation ${validated ? "was-validated" : ""}`}
        >
          <div className="form-group">
            <label htmlFor="branch">Branch <span className="text-danger">*</span></label>
            <select id="branch" name="branch" value={formData.branch} onChange={handleInputChange} required>
              <option value="" disabled>{branchName || 'Select a branch'}</option>
              {branches.map(branch => (
                <option key={branch.maChiNhanh} value={branch.maChiNhanh}>{branch.tenChiNhanh}</option>
              ))}
            </select>
            <div className="invalid-feedback">Please select a branch.</div>
          </div>

          <div className="form-group">
            <label htmlFor="cccd">
              CCCD <span className="text-danger">*</span>
            </label>
            <input
              type="text"
              id="cccd"
              name="cccd"
              value={formData.cccd}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">Please enter a valid CCCD.</div>
          </div>

          <div className="form-group">
            <label htmlFor="email">
              Email <span className="text-danger">*</span>
            </label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">
              Please enter a valid email address.
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="gender">
              Gender <span className="text-danger">*</span>
            </label>
            <select
              id="gender"
              name="gender"
              value={formData.gender}
              onChange={handleInputChange}
              required
            >
              <option value="" disabled>
                Select gender
              </option>
              <option value="M">Male</option>
              <option value="F">Female</option>
              <option value="O">Other</option>
            </select>
            <div className="invalid-feedback">Please select a gender.</div>
          </div>

          <div className="form-group">
            <label htmlFor="firstname">
              First Name <span className="text-danger">*</span>
            </label>
            <input
              type="text"
              id="firstname"
              name="firstname"
              value={formData.firstname}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">
              Please enter your first name.
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="lastname">
              Last Name <span className="text-danger">*</span>
            </label>
            <input
              type="text"
              id="lastname"
              name="lastname"
              value={formData.lastname}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">Please enter your last name.</div>
          </div>

          <div className="form-group">
            <label htmlFor="username">
              Username <span className="text-danger">*</span>
            </label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">Please enter a username.</div>
          </div>

          <div className="form-group">
            <label htmlFor="password">
              Password <span className="text-danger">*</span>
            </label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">Please enter a password.</div>
          </div>

          <div className="form-group">
            <label htmlFor="birthdate">
              Birthdate <span className="text-danger">*</span>
            </label>
            <input
              type="date"
              id="birthdate"
              name="birthdate"
              value={formData.birthdate}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">Please select a birthdate.</div>
          </div>

          <div className="form-group">
            <label htmlFor="phone">
              Phone <span className="text-danger">*</span>
            </label>
            <input
              type="tel"
              id="phone"
              name="phone"
              value={formData.phone}
              onChange={handleInputChange}
              required
            />
            <div className="invalid-feedback">
              Please enter a valid phone number.
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="resume">Resume</label>
            <input
              type="file"
              id="resume"
              name="resume"
              onChange={handleInputChange}
              accept=".pdf,.doc,.docx"
            />
          </div>

          <button type="submit" className="btn btn-danger">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default Recruit;







