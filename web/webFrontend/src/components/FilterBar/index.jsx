// src/components/FilterBar/index.jsx
import React from 'react';
import './styles.scss';

const FilterBar = ({
  categories,
  selectedCategory,
  onSelectCategory,
  sortOption,
  onSelectSortOption,
  priceRange,
  onSetPriceRange,
}) => {
  return (
    <div className="filter-bar">
      <label htmlFor="category-select">Danh mục:</label>
      <select
        id="category-select"
        value={selectedCategory}
        onChange={(e) => onSelectCategory(e.target.value)}
      >
        <option value="all">Tất cả</option>
        {categories.map((category) => (
          <option key={category} value={category}>
            {category}
          </option>
        ))}
      </select>

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

      <label htmlFor="min-price">Giá từ:</label>
      <input
        type="number"
        id="min-price"
        placeholder="Thấp nhất"
        value={priceRange.min}
        onChange={(e) => onSetPriceRange({ ...priceRange, min: e.target.value })}
      />

      <label htmlFor="max-price">đến:</label>
      <input
        type="number"
        id="max-price"
        placeholder="Cao nhất"
        value={priceRange.max}
        onChange={(e) => onSetPriceRange({ ...priceRange, max: e.target.value })}
      />
    </div>
  );
};

export default FilterBar;