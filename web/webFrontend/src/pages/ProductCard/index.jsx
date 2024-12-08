import React, { useContext } from 'react';
import './styles.scss';
import { useNavigate } from 'react-router-dom';
import { CartContext } from '../../context/CartContext';
import { Star } from 'lucide-react';

const ProductCard = (props) => {
  const navigate = useNavigate();
  const { addToCart } = useContext(CartContext);

  const handleClickProductCard = () => {
    navigate(`details/${props.id}`);
  };
  const moTa = props.moTa.length > 142 ? props.moTa.slice(0, 142) : props.moTa.padEnd(142);

  const handleAddToCart = () => {
    const product = {
      id: props.id,
      name: props.name,
      price: props.price,
      image: props.image
    };
    addToCart(product);
  };

  return (
    <div className="height d-flex justify-content-center align-items-center">
      <div className="card p-3">
        <div className="d-flex justify-content-between align-items-center ">
          <div className="mt-2">
            <h4 className="text-uppercase">4/5<Star color='#ffc107' size={20} />
                  <Star color='#ffc107' size={20} />
                  <Star color='#ffc107' size={20} />
                  <Star color='#ffc107' size={20} />
                  <Star size={20} /></h4>
            <div className="mt-3">
              <h5 className="text-uppercase mb-0">{props.loaiMonAn}</h5>
              <div className='productname'>
                <h1 className="main-heading mt-0">{props.name}</h1>
              </div>
              <div className="d-flex flex-row user-ratings">
                <div className="ratings">
                  
                </div>
                <h6 className="text-muted ml-1">{props.price} VND</h6>
              </div>
            </div>
          </div>
          <div className="image">
            <img className='image' onClick={handleClickProductCard} src={props.image || 'https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_3.jpg'} alt='' />
          </div>
        </div>

        <div className="d-flex justify-content-between align-items-center mt-auto">
          <span>Khẩu phần: {props.khauPhan}</span>
          {/* <div class="colors">
                  <span></span>
                  <span></span>
                  <span></span>
                  <span></span>
              </div> */}
        </div>
        <p className='button-container mt-auto'>{moTa}</p>

        <div className="button-container mt-auto">
          <button className="btnAddToCart btn btn-danger" onClick={handleAddToCart}>Add to cart</button>
        </div>
      </div>

    </div>
  )


  {/* <div className='card'>
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
    </div> */}
};

export default ProductCard;