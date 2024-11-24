import React, { useState, useEffect } from 'react';
import ProductCard from '../ProductCard/index';
import "./styles.scss";

const Menu = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState(''); // State for search input

  useEffect(() => {
    fetch('http://localhost:8080/monAn/all')
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setProducts(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching products:', error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  // Filter products based on search query
  const filteredProducts = products.filter((product) =>
    product.tenMonAn.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className='menu-container'>
      <div className='search-bar'>
        <input
          type='text'
          placeholder='🔎Search products...'
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
      </div>
      <div className='products-grid'>
        {filteredProducts.map((product) => (
          <ProductCard
            key={product.maMonAn}
            name={product.tenMonAn}
            price={product.gia}
          />
        ))}
      </div>
    </div>
  );
};

export default Menu;