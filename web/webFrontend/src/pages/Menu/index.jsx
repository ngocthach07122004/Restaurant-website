import React from 'react';
import ProductCard from '../ProductCard/index';
import "./styles.scss"

const Menu = () => {
  return (
    <div className='menu-container'>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
        <ProductCard image={''} name={'poison'} price={'10000'}/>
    </div>
  );
};


export default Menu;
