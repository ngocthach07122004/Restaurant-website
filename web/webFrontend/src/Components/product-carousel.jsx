
import React from "react";

const ProductCarousel = () => {
    return (
        <div>
            <div id="carouselExampleSlidesOnly" className="carousel slide" data-ride="carousel">
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <img className="d-block w-100" src="./dicktower.jpg" alt="First slide"/>
                    </div>
                    <div className="carousel-item">
                        <img className="d-block w-100" src="./dicktower.jpg" alt="Second slide"/>
                    </div>
                    <div className="carousel-item">
                        <img className="d-block w-100" src="./dicktower.jpg" alt="Third slide"/>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProductCarousel;
