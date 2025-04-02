import React, { useContext, useState } from "react";
import { CartContext } from "../../context/CartContext";
import { MoveLeft, Trash2, Plus, Minus } from "lucide-react";
import { useNavigate } from "react-router-dom";
import "./styles.scss";
import pictureImage from "../../DataStore/Picture";

function formatDate(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0"); // Tháng bắt đầu từ 0, cần cộng 1
  const day = String(date.getDate()).padStart(2, "0");
  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");
  const seconds = String(date.getSeconds()).padStart(2, "0");

  return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
}

// Ví dụ: "2024-12-07T15:30:00"

const Cart = () => {
  const cccdAccount = localStorage.getItem("cccd");
  const { cartItems, removeFromCart, updateQuantity } = useContext(CartContext);
  const [isPaymentSuccessful, setIsPaymentSuccessful] = useState(null);
  const [isSliding, setIsSliding] = useState(false);
  const navigate = useNavigate();
  const subTotal = cartItems.reduce(
    (total, item) => total + item.price * item.quantity,
    0
  );
  const discount = 0;
  const total = subTotal - discount;
  const totalItems = cartItems.reduce(
    (total, item) => total + item.quantity,
    0
  );

  const handleCreateOrder = () => {
    const orderData = {
      thoiGianDat: formatDate(new Date()),
      tongGiaTien: total,
      // listMaKhuyenMai: [
      //   { idKhuyenMai: "KM1" },
      //   { idKhuyenMai: "KM2" },
      //   { idKhuyenMai: "KM3" },
      // ],
      listMaKhuyenMai: [],
      maChiNhanh: { maChiNhanh: "CN001" },
      cccdKhachHang: cccdAccount,
      cccdNhanVienThuNgan: { maNhanVienThuNgan: "TN016" },
      tinhTrangThanhToan: "Dã thanh toán",
      phuongThucThanhToan: "Tiền mặt",
      listMonAnGioHang: cartItems.map((item) => ({
        monAn: { maMonAn: item.id },
        soLuong: item.quantity,
      })),
    };

    // console.log(orderData);

    fetch("http://localhost:8080/donMonAn/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(orderData),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log("Order created successfully:", data);
        setIsSliding(true);
        setTimeout(() => {
          setIsPaymentSuccessful(true);
        }, 500);
      })
      .catch((error) => {
        console.error("Error creating order:", error);
        setIsSliding(true);
        setTimeout(() => {
          setIsPaymentSuccessful(false);
        }, 500);
      });
  };

  if (isPaymentSuccessful) {
    navigate("/successPayment");
  }

  return (
    <div
      className={`cart-page container-fluid ${isSliding ? "slide-out" : ""}`}
    >
      <div className="card w-100">
        <div className="row">
          <div className="col-md-8 cart">
            <div className="title">
              <div className="row">
                <div className="col">
                  <h4>
                    <b>Shopping Cart</b>
                  </h4>
                </div>
                <div className="col align-self-center text-right text-muted">
                  {cartItems.length} products
                </div>
              </div>
            </div>
            {cartItems.length === 0 ? (
              <div className="alert alert-info text-center">
                Your cart is empty
              </div>
            ) : (
              <table className="table">
                <thead>
                  <tr>
                    <th>Image</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {cartItems.map((item, index) => (
                    <tr key={index}>
                      <td>
                        <img
                          className="img-fluid table-img"
                          src={
                            pictureImage[parseInt(item.image.slice(2))] ||
                            "https://via.placeholder.com/150"
                          }
                          alt={item.name}
                          width="80"
                          height="80"
                        />
                      </td>
                      <td>{item.name}</td>
                      <td>
                        <button
                          className="btn btn-outline-primary btn-sm"
                          type="button"
                          onClick={() =>
                            updateQuantity(
                              item.id,
                              Math.max(1, item.quantity - 1)
                            )
                          }
                          aria-label="Decrease quantity"
                        >
                          <Minus size={16} />
                        </button>
                        <span className="mx-2">{item.quantity}</span>
                        <button
                          className="btn btn-outline-primary btn-sm"
                          type="button"
                          onClick={() =>
                            updateQuantity(item.id, item.quantity + 1)
                          }
                          aria-label="Increase quantity"
                        >
                          <Plus size={16} />
                        </button>
                      </td>
                      <td>{item.price}₫</td>
                      <td>
                        <button
                          className="btn btn-link p-0"
                          onClick={() => removeFromCart(item.id)}
                          aria-label="Remove item"
                        >
                          <Trash2 color="#e10600" size={16} />
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            )}
            <div className="back-to-shop d-flex">
              <a href="/menu">
                <MoveLeft />
              </a>
              <span className="text-muted">Back to shop</span>
            </div>
          </div>
          <div className="col-md-4 summary">
            <div>
              <h5>
                <b>Summary</b>
              </h5>
            </div>
            <hr />
            <div className="row">
              <div className="col" style={{ paddingLeft: 0 }}>
                TOTAL PRODUCTS
              </div>
              <div className="col text-right">{totalItems}</div>
            </div>
            <form>
              <p>SHIPPING</p>
              <select>
                <option className="text-muted">Standard-Delivery- 5.00₫</option>
              </select>
              <p>GIVE CODE</p>
              <input id="code" placeholder="Enter your code" />
            </form>
            <div
              className="row"
              style={{
                borderTop: "1px solid rgba(0,0,0,.1)",
                padding: "2vh 0",
              }}
            >
              <div className="col">TOTAL PRICE</div>
              <div className="col text-right">{total}₫</div>
            </div>
            <button className="btn" onClick={handleCreateOrder}>
              CHECKOUT
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
