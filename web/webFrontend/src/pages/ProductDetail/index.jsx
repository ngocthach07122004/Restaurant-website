import React, { useState, useEffect, useContext } from 'react'
import { useParams } from 'react-router';
import Skeleton from 'react-loading-skeleton';
import { NavLink } from 'react-router-dom';
import { CartContext } from '../../context/CartContext';

function Product() {

    const { id } = useParams();
    const [product, setProduct] = useState([]);
    const [loading, setLoading] = useState(false);
    const { addToCart } = useContext(CartContext);
    const handleAddToCart = () => {
        const cartproduct = {
            id: product.id,
            name: product.name,
            price: product.price,
            image: product.image,
          };
        addToCart(cartproduct);
    };

    useEffect(() => {
        const getProduct = async () => {
            setLoading(true);
            const response = await fetch(`http://localhost:8080/monAn/${id}`);
            const data = await response.json();
            setProduct(data);
            console.log(data);
            setLoading(false);
        }
        getProduct();
    }, [id]);

    const Loading = () => {
        return (
            <>
                <div className="row d-flex justify-content-center">
                    <div className="col-md-12">
                        <NavLink className="text-decoration-none text-dark" to={`/`}>
                            <div className="d-flex align-items-center m-3">
                                <Skeleton height={20} width={50} />
                            </div>
                        </NavLink>
                        <div>
                            <div className="row">
                                <div className="col-md-6">
                                    <div className="images p-3">
                                        <div className="text-center p-4">
                                            <Skeleton height={300} width={250} />
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="border p-4">
                                        <div className="mt-4 mb-3"> <span className="text-uppercase text-muted brand">                                                <Skeleton height={30} width={150} />
                                        </span>
                                            <h5 className="text-uppercase">
                                                <Skeleton height={30} width={200} />
                                            </h5>
                                            <div className="price d-flex flex-row align-items-center">
                                                <span className="act-price">
                                                    <Skeleton height={20} width={70} />
                                                    <Skeleton height={30} width={100} />
                                                </span>
                                            </div>
                                        </div>
                                        <p className="about">
                                            <Skeleton height={10} width={300} />
                                            <Skeleton height={10} width={300} />
                                            <Skeleton height={10} width={300} />
                                            <Skeleton height={10} width={300} />
                                        </p>
                                        <div className="cart mt-4 align-items-center">
                                            <Skeleton height={40} width={150} />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        )
    }

    const ShowDetails = () => {
        return (
            <>
                <div className="row d-flex justify-content-center">
                    <div className="col-md-12">
                        <NavLink className="text-decoration-none text-dark" to={`/menu`}>
                            <div className="d-flex align-items-center m-3">
                                <i className="fa fa-long-arrow-left"></i>
                                <span className="ml-1">&nbsp;Back</span>
                            </div>
                        </NavLink>
                        <div>
                            <div className="row">
                                <div className="col-md-6">
                                    <div className="images">
                                        <div className="text-center ">
                                            <img id="main-image" alt="product" src={product.image?product.image:'https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_3.jpg'} width="450" height={450} />
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="border p-4">
                                        <div className="mt-4 mb-3">

                                        <span className="text-muted text-capitalize">{product.category}</span>

                                            <h5 className="text-uppercase">
                                                {product.tenMonAn}
                                            </h5>
                                            Rating: 5/5
                                            <i className="fa fa-star text-warning"></i>

                                            <div className="price d-flex flex-row align-items-center">
                                                <big className="display-6"><b>{product.gia} VND</b></big>
                                            </div>
                                        </div>
                                        <p className="text-muted">{product.moTa}</p>
                                        <div className="cart mt-4 align-items-center"> <button className="btn btn-outline-dark text-uppercase mr-2 px-4" onClick={handleAddToCart}>Buy</button> </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        )
    }

    return (
        <>
            <div className="container px-0 mb-5" style={{ marginTop: "66px" }}>

                {loading ? <Loading /> : <ShowDetails />}

            </div>
        </>
    )
}

export default Product