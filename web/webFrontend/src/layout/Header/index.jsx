// src/components/Header.jsx
import React, { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Menu } from 'lucide-react';
import logo from '../../assets/jollibee.png';
import tel from '../../assets/delivery-lg-rs.png';
import './styles.css';


const Header = () => {
  const navigate = useNavigate();
  const [isNavCollapsed, setIsNavCollapsed] = useState(true);
  const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);
  const location = useLocation();

  const navLinks = [
    { path: '/', label: 'TRANG CHỦ' },
    { path: '/about', label: 'VỀ JOLLIBEE' },
    { path: '/menu', label: 'THỰC ĐƠN' },
    { path: '/promotions', label: 'KHUYẾN MÃI' },
    { path: '/services', label: 'DỊCH VỤ' },
    { path: '/news', label: 'TIN TỨC' },
    { path: '/stores', label: 'CỬA HÀNG' },
    { path: '/contact', label: 'LIÊN HỆ' },
    { path: '/careers', label: 'TUYỂN DỤNG' }
  ];

  return (
    <>
      {/* Top Bar */}
      <div className="d-none d-lg-block" style={{ backgroundColor: '#FFB700' }}>
        <div className="container d-flex justify-content-end py-2 gap-4">
          <div className="d-flex gap-3 align-items-center">
            <button className="btn btn-link text-danger p-0">VN</button>
            <span className="text-danger">|</span>
            <button className="btn btn-link text-danger p-0">EN</button>
          </div>
          <div className="d-flex gap-3 align-items-center">
            <i className="bi bi-geo-alt-fill text-danger"></i>
            <span className="text-danger">HỒ CHÍ MINH</span>
          </div>
          <div className="d-flex gap-3 align-items-center">
            <i className="bi bi-person-fill text-danger"></i>
            <span className="text-danger">
              <a 
                onClick={() => navigate('signup')} 
                style={{ color: 'black', fontSize:'14px',fontWeight: 'bold', cursor: 'pointer', textDecoration: 'none' }}
                onMouseOver={(e) => e.currentTarget.style.textDecoration = 'underline'}
                onMouseOut={(e) => e.currentTarget.style.textDecoration = 'none'}
              >
                ĐĂNG KÝ
              </a> 
              / 
              <a 
                onClick={() => navigate('signin')} 
                style={{ color: 'black', fontSize:'14px', fontWeight: 'bold', cursor: 'pointer', textDecoration: 'none' }}
                onMouseOver={(e) => e.currentTarget.style.textDecoration = 'underline'}
                onMouseOut={(e) => e.currentTarget.style.textDecoration = 'none'}
              >
                ĐĂNG NHẬP
              </a>
            </span>
          </div>
        </div>
      </div>

      {/* Main Navigation */}
      <nav className="main-nav navbar navbar-expand-lg" style={{ backgroundColor: '#E31837' }}>
        <div className="container-fluid">
          {/* Mobile Menu Button */}
          <button
            className="navbar-toggler border-0 d-lg-none"
            type="button"
            onClick={handleNavCollapse}
          >
            <Menu color="white" />
          </button>

          {/* Logo - Centered on mobile */}
          <Link className="navbar-brand mx-auto mx-lg-0" to="/">
            <img
              src={logo}
              alt="Jollibee Logo"
              height="80"
            />
          </Link>

          {/* Desktop Navigation */}
          <div className={`${isNavCollapsed ? 'collapse' : ''} navbar-collapse`}>
            <ul className="nav-list navbar-nav me-auto mb-2 mb-lg-0">
              {navLinks.map(({ path, label }) => (
                <li key={path} className="nav-item">
                  <Link
                    to={path}
                    className={`nav-link ${location.pathname === path ? 'active' : ''}`}
                  >
                    <span className='fw-bold fs-6'>{label}</span>
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          {/* Right Side Buttons */}
          <div className="d-flex gap-3 align-items-center">
            <button className="btn btn-warning rounded-pill fw-bold px-4">
              PICK UP
            </button>
            <div className="d-none d-lg-flex align-items-center text-white">
              <img src={tel} alt="Giao hàng tận nơi" height="40" />
            </div>
          </div>
        </div>
      </nav>
    </>
  );
};

export default Header;