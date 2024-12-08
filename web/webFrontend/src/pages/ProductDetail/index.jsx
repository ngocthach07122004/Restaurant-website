import React, { useState, useEffect, useContext } from 'react';
import { useParams } from 'react-router';
import Skeleton from 'react-loading-skeleton';
import { NavLink } from 'react-router-dom';
import { MoveLeft, Star } from 'lucide-react';
import './styles.scss';
import 'react-loading-skeleton/dist/skeleton.css';
import { CartContext } from '../../context/CartContext';

function Product() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const { addToCart } = useContext(CartContext);

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                setLoading(true);
                const response = await fetch(`http://localhost:8080/monAn/${id}`);
                const data = await response.json();
                setProduct(data);
            } catch (error) {
                console.error("Error fetching product data:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchProduct();
    }, [id]);

    const handleAddToCart = () => {
      const productToAdd = {
        id: product.maMonAn,
        name: product.tenMonAn,
        price: product.gia,
        image: product.anhMonAn,
      }  
      addToCart(productToAdd);
    };

    const LoadingSkeleton = () => (
        <div className="container product-skeleton py-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <Skeleton height={400} width="100%" />
                </div>
                <div className="col-md-6">
                    <Skeleton height={40} width="80%" />
                    <Skeleton height={30} width="50%" />
                    <Skeleton height={20} width="60%" />
                    <Skeleton height={20} width="60%" />
                    <Skeleton height={50} width="40%" />
                </div>
            </div>
        </div>
    );

    const ProductDetails = () => (
        <div className="container product-page">
            <div className="row justify-content-center">
                <div className="col-lg-10 product-container">
                    <NavLink to="/menu" className="back-link btn btn-outline-primary">
                        <MoveLeft /> Back to Menu
                    </NavLink>
                    <div className="row">
                        <div className="col-md-6 text-center justify-content-center align-items-center">
                            <img
                                src={product.anhMonAn || 'https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_3.jpg'}
                                alt={product.tenMonAn || "Product"}
                                className="product-image img-fluid"
                            />
                        </div>
                        <div className="col-md-6">
                            <div className="product-details">
                                <h2 className="product-title">{product.tenMonAn}</h2>
                                <p className="product-category">{product.category}</p>
                                <div className="product-rating">
                                    Rating: 5/5 <Star />
                                    <i className="fa fa-star rating-icon"></i>
                                </div>
                                <h3 className="product-price">
                                    {product.gia} VND
                                </h3>
                                {/* <p className="product-description">{product.moTa}</p> */}
                                <button className="btn btn-buy">
                                    Buy Now
                                </button>
                                <button className="btn btn-cart" onClick={handleAddToCart}>
                                    Add to Cart
                                </button>
                            </div>
                            <div className="product-details-section">
                                <h3>Chi tiết</h3>
                                <p className="text-muted">
                                    {product.moTa}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );

    return (
        <>
            {loading ? <LoadingSkeleton /> : product && <ProductDetails />}
        </>
    );
}

export default Product;