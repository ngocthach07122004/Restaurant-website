// src/components/FilterBar/index.jsx
import React from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';
import './styles.scss';

const FilterBar = ({
  categories,
  selectedCategory,
  onSelectCategory,
  sortOption,
  onSelectSortOption,
  priceRange,
  onSetPriceRange,
  minPrice,
  maxPrice,
}) => {
  return (
    <div className="filter-bar">
      {/* Category Filter */}
      <label htmlFor="category-select">Danh mục:</label>
      <select
        id="category-select"
        value={selectedCategory}
        onChange={(e) => onSelectCategory(e.target.value)}
      >
        <option value="all">Tất cả</option>
        {categories.map((category, index) => (
          <option key={index} value={category}>
            {category}
          </option>
        ))}
      </select>

      {/* Sort Option */}
      <label htmlFor="sort-select">Sắp xếp theo giá:</label>
      <select
        id="sort-select"
        value={sortOption}
        onChange={(e) => onSelectSortOption(e.target.value)}
      >
        <option value="none">Mặc định</option>
        <option value="asc">Giá tăng dần</option>
        <option value="desc">Giá giảm dần</option>
      </select>

      {/* Price Range Slider */}
      <label>Khoảng giá:</label>
      <div className="price-range-slider">
        <Slider
          min={minPrice}
          max={maxPrice}
          value={[priceRange.min, priceRange.max]}
          onChange={(values) => {
            onSetPriceRange({ min: values[0], max: values[1] });
          }}
        />
        <div className="price-labels">
          <span>{priceRange.min}.000₫</span>
          <span>{priceRange.max}.000₫</span>
        </div>
      </div>
    </div>
  );
};

export default FilterBar;