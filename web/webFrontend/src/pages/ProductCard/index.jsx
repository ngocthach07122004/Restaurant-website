import React from 'react';
import "./styles.scss"
import { useNavigate } from 'react-router-dom';


const ProductCard = (props) => {
  const navigate = useNavigate();
  const handleClickProductCard = () => {
    navigate('')
  }
  return (
    <div className='card'>
      <div>
        <img onClick={handleClickProductCard} className='image' alt={props.name} src='https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_3.jpg' />
      </div>
      <h3 className='name'>{props.name?props.name:"poison food"}</h3>
      <p className='price'>{props.price?props.price:"110.000"}.000đ</p>
      <div><button className='button'>Add to cart</button></div>
    </div>
  );
};

export default ProductCard;
