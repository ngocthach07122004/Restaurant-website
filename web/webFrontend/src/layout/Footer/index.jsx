import React from 'react';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer className="bg-dark text-light py-4 mt-auto">
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <h5>About Jollibee</h5>
            <ul className="list-unstyled">
              <li><Link className="text-light text-decoration-none" to="/about">Our Story</Link></li>
              <li><Link className="text-light text-decoration-none" to="/careers">Careers</Link></li>
              <li><Link className="text-light text-decoration-none" to="/news">News</Link></li>
            </ul>
          </div>
          <div className="col-md-4">
            <h5>Support</h5>
            <ul className="list-unstyled">
              <li><Link className="text-light text-decoration-none" to="/contact">Contact Us</Link></li>
              <li><Link className="text-light text-decoration-none" to="/faq">FAQ</Link></li>
              <li><Link className="text-light text-decoration-none" to="/terms">Terms & Conditions</Link></li>
            </ul>
          </div>
          <div className="col-md-4">
            <h5>Connect With Us</h5>
            <div className="d-flex gap-3">
              <a href="#" className="text-light"><i className="bi bi-facebook"></i></a>
              <a href="#" className="text-light"><i className="bi bi-twitter"></i></a>
              <a href="#" className="text-light"><i className="bi bi-instagram"></i></a>
            </div>
          </div>
        </div>
        <div className="row mt-4">
          <div className="col text-center">
            <p className="mb-0">© 2024 Jollibee. All Rights Reserved.</p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;