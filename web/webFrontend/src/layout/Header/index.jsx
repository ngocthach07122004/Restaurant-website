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
              <button onClick={() => navigate('signup')}>ĐĂNG KÝ</button> 
              / 
              <button onClick={() => navigate('signin')}>ĐĂNG NHẬP</button> 
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

              {/* <li className="nav-item">
                <NavLink className={({ isActive }) =>
                  `nav-link text-white position-relative ${isActive ? 'active-nav' : ''}`
                }
                  to="/">
                  <span className='fw-bold fs-6'>TRANG CHỦ</span>
                </NavLink>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/about">
                  <span className='fw-bold fs-6'>VỀ JOLLIBEE</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/menu">
                  <span className='fw-bold fs-6'>THỰC ĐƠN</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/promotions">
                  <span className='fw-bold fs-6'>KHUYẾN MÃI</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/services">
                  <span className='fw-bold fs-6'>DỊCH VỤ</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/news">
                  <span className='fw-bold fs-6'>TIN TỨC</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/stores">
                  <span className='fw-bold fs-6'>CỬA HÀNG</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/contact">
                  <span className='fw-bold fs-6'>LIÊN HỆ</span>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link text-white" to="/careers">
                  <span className='fw-bold fs-6'>TUYỂN DỤNG</span>
                </Link>
              </li> */}
            </ul>
          </div>

          {/* Right Side Buttons */}
          <div className="d-flex gap-3 align-items-center">
            <button className="btn btn-warning rounded-pill fw-bold px-4">
              PICK UP
            </button>
            <div className="d-none d-lg-flex align-items-center text-white">
              {/* <i className="bi bi-telephone-fill me-2"></i>
              <span>1900-1533</span>
              <small className="d-block text-uppercase ms-2" style={{ fontSize: '0.7rem' }}>
                GIAO HÀNG TẬN NƠI
              </small> */}
              <img src={tel} alt="Giao hàng tận nơi" height="40" />
            </div>
          </div>
        </div>
      </nav>
    </>
  );
};

export default Header;