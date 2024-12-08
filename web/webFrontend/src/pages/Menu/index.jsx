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
  const [priceRange, setPriceRange] = useState({ min: 0, max: 100000 });
  const [minPrice, setMinPrice] = useState(0);
  const [maxPrice, setMaxPrice] = useState(100000);

  useEffect(() => {
    fetch('http://localhost:8080/monAn/all')
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setProducts(data);
        setLoading(false);

        const uniqueCategories = [
          ...new Set(data.map((product) => product.loaiMonAn)),
        ];
        setCategories(uniqueCategories);

        // Determine min and max prices
        const prices = data.map((product) => product.gia);
        const minProductPrice = Math.min(...prices);
        const maxProductPrice = Math.max(...prices);
        setMinPrice(minProductPrice);
        setMaxPrice(maxProductPrice);
        setPriceRange({ min: minProductPrice, max: maxProductPrice });
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
      product.tenMonAn
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .toLowerCase()
        .includes(
          searchQuery
            .normalize('NFD')
            .replace(/[\u0300-\u036f]/g, '')
            .toLowerCase()
        )
    )
    .filter((product) =>
      selectedCategory === 'all' ? true : product.loaiMonAn === selectedCategory
    );

  // Filter products based on price range
  filteredProducts = filteredProducts.filter((product) => {
    const price = product.gia;
    return price >= priceRange.min && price <= priceRange.max;
  });

  // Sort products based on price
  if (sortOption === 'asc') {
    filteredProducts.sort((a, b) => a.gia - b.gia);
  } else if (sortOption === 'desc') {
    filteredProducts.sort((a, b) => b.gia - a.gia);
  }

  return (
    <div className="menu-container"  style={{ 
      backgroundImage: `url("https://via.placeholder.com/500")` 
    }}>
      <SearchBar
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        placeholder="🔎 Tìm kiếm sản phẩm..."
      />
      <FilterBar
        categories={categories}
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
        sortOption={sortOption}
        onSelectSortOption={setSortOption}
        priceRange={priceRange}
        onSetPriceRange={setPriceRange}
        minPrice={minPrice}
        maxPrice={maxPrice}
      />
      <div className="products-grid">
        {filteredProducts.map((product) => (
          <ProductCard
            key={product.maMonAn}
            id={product.maMonAn}
            name={product.tenMonAn?(product.tenMonAn.length>30?product.tenMonAn.slice(0, 27)+"...": product.tenMonAn):""}
            price={product.gia}
            khauPhan={product.khauPhan?product.khauPhan:1}
            loaiMonAn={product.loaiMonAn?product.loaiMonAn:""} 
            moTa={product.moTa?(product.moTa.length>70?product.moTa.slice(0, 67)+"...": product.moTa):""}
            // image={product.image}
          />
        ))}
      </div>
    </div>
  );
};

export default Menu;