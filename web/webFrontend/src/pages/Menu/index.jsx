// src/pages/Menu/index.jsx
import React, { useState, useEffect } from 'react';
import ProductCard from '../ProductCard';
import SearchBar from '../../components/SearchBar';
import FilterBar from '../../components/FilterBar';
import './styles.scss';

const Menu = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('all');
  const [categories, setCategories] = useState([]);
  const [sortOption, setSortOption] = useState('none');
  const [priceRange, setPriceRange] = useState({ min: '', max: '' });

  useEffect(() => {
    fetch('http://localhost:8080/monAn/all')
      .then((response) => response.json())
      .then((data) => {
        setProducts(data);
        setLoading(false);

        const uniqueCategories = [
          ...new Set(data.map((product) => product.loaiMonAn)),
        ];
        setCategories(uniqueCategories);
      })
      .catch((error) => {
        console.error('Error fetching products:', error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  // Filter products based on search query and category
  let filteredProducts = products
    .filter((product) =>
      product.tenMonAn.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase().includes(searchQuery.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase())
    )
    .filter((product) =>
      selectedCategory === 'all' ? true : product.loaiMonAn === selectedCategory
    );

  // Filter products based on price range
  const minPrice = parseFloat(priceRange.min);
  const maxPrice = parseFloat(priceRange.max);

  filteredProducts = filteredProducts.filter((product) => {
    const price = product.gia;
    if (!isNaN(minPrice) && price < minPrice) {
      return false;
    }
    if (!isNaN(maxPrice) && price > maxPrice) {
      return false;
    }
    return true;
  });

  // Sort products based on price
  if (sortOption === 'asc') {
    filteredProducts.sort((a, b) => a.gia - b.gia);
  } else if (sortOption === 'desc') {
    filteredProducts.sort((a, b) => b.gia - a.gia);
  }

  return (
    <div className='menu-container'>
      <SearchBar
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        placeholder='🔎 Tìm kiếm sản phẩm...'
      />
      <FilterBar
        categories={categories}
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
        sortOption={sortOption}
        onSelectSortOption={setSortOption}
        priceRange={priceRange}
        onSetPriceRange={setPriceRange}
      />
      <div className='products-grid'>
        {filteredProducts.map((product) => (
          <ProductCard
            key={product.maMonAn}
            name={product.tenMonAn}
            price={product.gia}
            image={product.image}
          />
        ))}
      </div>
    </div>
  );
};

export default Menu;