import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './styles.scss';

const Recruit = () => {
  const [branches, setBranches] = useState([]);
  const [formData, setFormData] = useState({
    branch: '',
    name: '',
    email: '',
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
          alt="Sample photo"
        />
        <h1>Come join us</h1>
        <form onSubmit={handleSubmit} noValidate validated={validated.toString()} className={`needs-validation ${validated ? 'was-validated' : ''}`}>
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
            <label htmlFor="name">Name <span className="text-danger">*</span></label>
            <input type="text" id="name" name="name" value={formData.name} onChange={handleInputChange} required />
            <div className="invalid-feedback">Please enter your name.</div>
          </div>
          <div className="form-group">
            <label htmlFor="email">Email <span className="text-danger">*</span></label>
            <input type="email" id="email" name="email" value={formData.email} onChange={handleInputChange} required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" />
            <div className="invalid-feedback">Please enter a valid email address.</div>
          </div>
          <div className="form-group">
            <label htmlFor="resume">Resume</label>
            <input type="file" id="resume" name="resume" onChange={handleInputChange} accept=".pdf,.doc,.docx" />
          </div>
          <button type="submit" className="btn btn-danger">Submit Application</button>
        </form>
      </div>
    </div>
  );
};

export default Recruit;