import React, { useState, useContext } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { Menu, ShoppingBag, User } from "lucide-react";
import logo from "../../assets/jollibee.png";
import tel from "../../assets/delivery-lg-rs.png";
import bogoBk from "../../assets/HCMUT_official_logo.png";
import { CartContext } from "../../context/CartContext";
import BellNotification from "../../components/BellNotification/";
import "./styles.css";
import styles from "./styles.module.scss";
import classNames from "classnames/bind";
const cx = classNames.bind(styles);
const Header = () => {
  const navigate = useNavigate();
  const [stateBellNotification, setStateBellNotification] = useState(false);
  const { cartItems } = useContext(CartContext);
  const [isNavCollapsed, setIsNavCollapsed] = useState(true);
  const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);
  const location = useLocation();
  const handleBellNotificationClick = () => {
    setStateBellNotification(!stateBellNotification);
  };
  const navLinks = [
    { path: "/", label: "TRANG CHỦ" },
    { path: "/about", label: "VỀ PIKABEE" },
    { path: "/menu", label: "THỰC ĐƠN" },
    { path: "/branch", label: "CHI NHÁNH" },
    { path: "/contact", label: "LIÊN HỆ" },
    { path: "/careers", label: "TUYỂN DỤNG" },
  ];

  const userData = localStorage.getItem("userdata");
  // const username = userData ? JSON.parse(userData).username : "---";
  const username = localStorage.getItem("name");
  return (
    <>
      {/* Top Bar */}

      {/* Main Navigation */}
      <nav
        className={cx(
          "wrapper_main_header",
          "main-nav",
          "navbar",
          "navbar-expand-lg"
        )}
        style={{ backgroundColor: "#E31837" }}
      >
        <div className="container-fluid">
          {/* Mobile Menu Button */}
          <button
            className="navbar-toggler border-0 d-lg-none"
            type="button"
            onClick={handleNavCollapse}
            aria-label="Toggle navigation"
          >
            <Menu color="white" />
          </button>

          {/* Logo - Centered on mobile */}
          <Link className="navbar-brand mx-auto mx-lg-0" to="/">
            <img
              className="wrapperLogoBk"
              src={bogoBk}
              alt="Logo"
              height="80"
            />
          </Link>

          {/* Desktop Navigation */}
          <div
            className={`${isNavCollapsed ? "collapse" : ""} navbar-collapse`}
          >
            <ul className="nav-list navbar-nav me-auto mb-2 mb-lg-0">
              {navLinks.map(({ path, label }) => (
                <li key={path} className="nav-item">
                  <Link
                    to={path}
                    className={`nav-link ${
                      location.pathname === path ? "active" : ""
                    }`}
                  >
                    <span className="fw-bold fs-6">{label}</span>
                  </Link>
                </li>
              ))}
            </ul>

            {/* ✨ THÊM PHẦN NÀY Ở ĐÂY ✨ */}
            <div className="d-flex flex-column flex-lg-row gap-3 align-items-start align-items-lg-center ms-lg-auto">
              <div
                className={cx(
                  "d-flex",
                  "gap-2",
                  "align-items-center",
                  "wrapper_language"
                )}
              >
                <button className="btn btn-link text-white p-0">VN</button>
                <span className="text-white">|</span>
                <button className="btn btn-link text-white p-0">EN</button>
              </div>
              <div
                className={cx(
                  "d-flex",
                  "gap-2",
                  "align-items-center",
                  "text-white",
                  "wrapper_handle_response"
                )}
              >
                <i className="bi bi-geo-alt-fill"></i>
                <span className={cx("wrapper_position")}>HỒ CHÍ MINH</span>
              </div>
              <div
                className={cx(
                  "d-flex",
                  "gap-2",
                  "align-items-center",
                  "text-white",
                  "wrapper_handle_response"
                )}
              >
                <i className="bi bi-person-fill wrapper_handle_response"></i>
                {localStorage.getItem("login") !== "success" ? (
                  <span>
                    <a
                      onClick={() => navigate("signup")}
                      className={cx(
                        "fw-bold",
                        "text-white",
                        "text-decoration-none",
                        "wrapper_signup_signin"
                      )}
                    >
                      ĐĂNG KÝ
                    </a>
                    /
                    <a
                      onClick={() => navigate("signin")}
                      className={cx(
                        "fw-bold",
                        "text-white",
                        "text-decoration-none",
                        "wrapper_signup_signin"
                      )}
                    >
                      ĐĂNG NHẬP
                    </a>
                  </span>
                ) : (
                  <div className="d-flex gap-2 align-items-center">
                    <Link to="/profile">
                      <User className="text-white" />
                    </Link>
                    <Link to="/cart">
                      <div className="nav-bag position-relative">
                        <ShoppingBag className="text-white" size={24} />
                        <span className="badge bg-danger position-absolute top-0 start-100 translate-middle rounded-pill">
                          {cartItems.length}
                        </span>
                      </div>
                    </Link>
                    <div onClick={handleBellNotificationClick}>
                      <BellNotification
                        stateNotification={stateBellNotification}
                        setStateBellNotification={setStateBellNotification}
                      />
                    </div>
                    <span className="fw-bold text-white">
                      <a
                        onClick={() => navigate("/profile")}
                        className="text-white text-decoration-none"
                      >
                        {username}
                      </a>
                      /
                      <a
                        onClick={() => {
                          localStorage.clear();
                          navigate("/");
                        }}
                        className="text-white text-decoration-none"
                      >
                        Đăng xuất
                      </a>
                    </span>
                  </div>
                )}
              </div>
            </div>
          </div>

          {/* Right Side Buttons */}
          <div
            className={cx(
              "d-flex",
              "gap-3",
              "align-items-center",
              "wrapper_right_side_nav"
            )}
          >
            {/* <button className="btn btn-warning rounded-pill fw-bold px-4">
              PICK UP
            </button>
            <div className="d-none d-lg-flex align-items-center text-white">
              <img src={tel} alt="Giao hàng tận nơi" height="40" />
            </div> */}
            {/* <div className="container d-flex justify-content-end py-2 gap-4"> */}
          </div>
        </div>
      </nav>
    </>
  );
};

export default Header;
