import React, { useContext } from 'react';
import { CartContext } from '../../context/CartContext';
import { Trash2 } from 'lucide-react'
// import './styles.scss';

const Cart = () => {
  const { cartItems } = useContext(CartContext);

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Your Cart</h2>
      {cartItems.length === 0 ? (
        <div className="alert alert-info text-center">
          Your cart is empty
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-bordered table-hover">
            <thead className="thead-dark">
              <tr className='text-center'>
                <th scope="col">Image</th>
                <th scope="col">Product Name</th>
                <th scope="col">Price</th>
                <th scope='col'>Action</th>
              </tr>
            </thead>
            <tbody>
              {cartItems.map((item, index) => (
                <tr key={index} className='text-center'>
                  <td className="text-center">
                    <img
                      src={item.image}
                      alt={item.name}
                      width="50"
                      className="img-fluid"
                    />
                  </td>
                  <td>{item.name}</td>
                  <td>{item.price.toLocaleString()}.000₫</td>
                  <td><Trash2 color='#e10600' /></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default Cart;