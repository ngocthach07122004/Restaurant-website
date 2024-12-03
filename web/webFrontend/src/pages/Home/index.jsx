import React from 'react';
import limb from "../../assets/limb.jpg";
import hamburger from "../../assets/hamburger.jpg";
import dessert from "../../assets/dessert.jpg";
import './styles.scss'; 

const Home = () => {
  return (
    <div id="carouselExampleIndicators" className="carousel slide">
      <div className="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
      </div>
      <div className="carousel-inner">
        <div className="carousel-item active">
          <img src={limb} className="d-block w-100" alt="Slide 1"/>
          <div className="carousel-caption">
            <h2>Welcome to Our Restaurant</h2>
            <p>Enjoy the best food in town</p>
          </div>
        </div>
        <div className="carousel-item">
          <img src={hamburger} className="d-block w-100" alt="Slide 2"/>
          <div className="carousel-caption">
            <h2>Delicious Burgers</h2>
            <p>Try our special burgers</p>
          </div>
        </div>
        <div className="carousel-item">
          <img src={dessert} className="d-block w-100" alt="Slide 3"/>
          <div className="carousel-caption">
            <h2>Fresh Ingredients</h2>
            <p>We use only the freshest ingredients</p>
          </div>
        </div>
      </div>
      <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default Home;