import React, { useContext } from 'react';
import './styles.scss';
import { useNavigate } from 'react-router-dom';
import { CartContext } from '../../context/CartContext';

const ProductCard = (props) => {
  const navigate = useNavigate();
  const { addToCart } = useContext(CartContext);

  const handleClickProductCard = () => {
    navigate('');
  };

  const handleAddToCart = () => {
    const product = {
      id: props.id,
      name: props.name,
      price: props.price,
      image: props.image,
    };
    addToCart(product);
  };

  return (
    <div className='card'>
      <div>
        <img
          onClick={handleClickProductCard}
          className='image'
          alt={props.name}
          src={props.image || 'https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_3.jpg'}
        />
      </div>
      <h3 className='name'>{props.name || 'Product Name'}</h3>
      <p className='price'>{props.price || '0'}.000đ</p>
      <div>
        <button className='button' onClick={handleAddToCart}>Add to cart</button>
      </div>
    </div>
  );
};

export default ProductCard;