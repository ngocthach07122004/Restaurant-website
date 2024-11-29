import React, { useContext } from 'react';
import { CartContext } from '../../context/CartContext';
import { Trash2 } from 'lucide-react';

const Cart = () => {
  const { cartItems, removeFromCart } = useContext(CartContext);
  console.log(cartItems);
  const subTotal = cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  const discount = 0;
  const total = subTotal - discount;

  return (
    <div className="container mt-5">
      <div className="row">
        {/* Left Section */}
        <div className="col-lg-8">
          <h2 className="text-center mb-4">Thông Tin Giỏ Hàng</h2>
          {cartItems.length === 0 ? (
            <div className="alert alert-info text-center">
              Tất cả: 0 sản phẩm
            </div>
          ) : (
            <div className="table-responsive">
              <table className="table table-bordered table-hover">
                <thead>
                  <tr className="text-center">
                    <th scope="col">Tất cả: {cartItems.length} sản phẩm</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Thành tiền</th>
                    <th scope="col">
                      <Trash2 color="#e10600" />
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {cartItems.map((item, index) => (
                    <tr key={index} className="text-center">
                      <td>{item.name}</td>
                      <td>{item.price.toLocaleString()}₫</td>
                      <td>{(item.price * item.quantity).toLocaleString()}₫</td>
                      <td>
                        <button 
                          className="btn btn-link p-0" 
                          onClick={() => removeFromCart(item.id)} 
                          aria-label="Remove item"
                        >
                          <Trash2 color="#e10600" />
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>

        {/* Right Section */}
        <div className="col-lg-4">
          <div className="bg-light p-3 rounded shadow-sm">
            <h5>Thông tin bổ sung (CCCD, BHXH): <a href="#">Thay đổi</a></h5>
            <hr />
            <p>Tạm tính: <span className="float-right">{subTotal.toLocaleString()}.000₫</span></p>
            <p>Giảm giá: <span className="float-right">{discount.toLocaleString()}%</span></p>
            <h5 className="text-danger">
              Tổng tiền: <span className="float-right">{total.toLocaleString()}.000₫</span>
            </h5>
            <small className="text-muted">(Đã bao gồm VAT nếu có)</small>
            <div className="mt-3">
              <button className="btn btn-danger btn-block">Đặt Dịch Vụ</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
