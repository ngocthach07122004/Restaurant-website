import React, { useState, useEffect, useContext } from "react";
import { useParams } from "react-router";
import Skeleton from "react-loading-skeleton";
import { NavLink } from "react-router-dom";
import { MoveLeft, Star } from "lucide-react";
import "./styles.scss";
import "react-loading-skeleton/dist/skeleton.css";
import { CartContext } from "../../context/CartContext";
import { ThumbsUp, ThumbsDown, Reply } from "lucide-react";
import pictureImage from "../../DataStore/Picture";
import styles from "./styles.scss";
import classNames from "classnames/bind";
const cx = classNames.bind(styles);

function Product() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const { addToCart } = useContext(CartContext);
  const [comment, setComment] = useState("");

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
  console.log("123");
  const handleAddToCart = () => {
    const productToAdd = {
      id: product.maMonAn,
      name: product.tenMonAn,
      price: product.gia,
      image: product.anhMonAn,
    };
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
                src={
                  pictureImage[parseInt(product.anhMonAn.slice(2))] ||
                  "https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_3.jpg"
                }
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
                <h3 className="product-price">{product.gia} VND</h3>
                {/* <p className="product-description">{product.moTa}</p> */}
                <button className="btn btn-buy">Buy Now</button>
                <button className="btn btn-cart" onClick={handleAddToCart}>
                  Add to Cart
                </button>
              </div>
              <div className="product-details-section">
                <h3>Chi tiết</h3>
                <p className="text-muted">{product.moTa}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Comments box */}
      {/* 
      <div
        className={cx(
          "wrapper_box_commentComponent",
          "d-flex",
          "flex-row",
          "align-items-center",
          "mb-4"
        )}
      > */}
      <div className="form-outline flex-fill mb-0">
        <textarea
          className={cx("wrapper_box_comment", "form-control")}
          id="textAreaExample"
          rows="4"
          placeholder="Bình luận của bạn về món ăn này"
          value={comment}
          onChange={(e) => {
            console.log(comment);
            setComment(e.target.value);
          }}
        ></textarea>
      </div>
      {/* </div> */}

      <div className="row d-flex justify-content-center mt-5">
        <div className="col-md-11 col-lg-9 col-xl-7">
          <div className="d-flex flex-start mb-4">
            <img
              src="https://th.bing.com/th/id/OIP.vSLjVawLO3L3rbX3WtbJNwHaHa?rs=1&pid=ImgDetMain"
              alt="avatar"
              className="rounded-circle shadow-1-strong me-3"
              width="60"
              height="60"
            />

            <div className="card w-100">
              <div className="card-body p-4">
                <div>
                  <h5>Le Huy</h5>
                  <p className="small">3 hours ago</p>
                  <p>
                    Cứ mỗi buổi sáng, từ sáu giờ cho đến bảy giờ, chỉ trong
                    quảng ấy thôi, vì ngoài giờ gánh phở hết, chung quanh nồi
                    nước phở, ta thấy tụm năm tụm ba, các bệnh nhân đàn ông và
                    đàn bà, các bác gác san, các thầy y tá, và cả đến các học
                    sinh trường Thuốc nữa. Chừng ấy người đều hợp lòng trong sự
                    thưởng thức món quà ngon, nâng cách ăn phở lên đến một nghệ
                    thuật đáng kính.
                  </p>
                  <div className="d-flex justify-content-between align-items-center">
                    <div className="d-flex align-items-center">
                      <button className="btn btn-outline-primary">
                        <ThumbsUp />
                      </button>
                      <button className="btn btn-outline-danger ms-3">
                        <ThumbsDown />
                      </button>
                    </div>
                    <button className="btn btn-outline-primary">
                      <Reply />
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

  return <>{loading ? <LoadingSkeleton /> : product && <ProductDetails />}</>;
}

export default Product;
