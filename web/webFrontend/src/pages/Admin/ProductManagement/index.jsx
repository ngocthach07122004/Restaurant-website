import React, { useEffect, useState } from "react";
import { Space, Table, Tag, Modal, Button, Descriptions } from 'antd';
import { toast } from "react-toastify";
import CreateProductsForm from "../../../components/AdminComponent/CreateProductsForm";

const ProductManagement = () => {
  const [products, setProducts] = useState([]);
  const [isDetailModalVisible, setIsDetailModalVisible] = useState(false);
  const [isAddProductModalVisible, setIsAddProductModalVisible] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState(null);

  const handleShowDetail = (maMonAn) => {
    const product = products.find((u) => u.maMonAn === maMonAn);
    setSelectedProduct(product);
    setIsDetailModalVisible(true);
  };

  const handleCloseDetailModal = () => {
    setIsDetailModalVisible(false);
    setSelectedProduct(null);
  };

  const handleAddProduct = () => {
    setIsAddProductModalVisible(true);
  };

  const handleCloseAddProductModal = () => {
    setIsAddProductModalVisible(false);
  };

  const handleRemove = (maMonAn) => {
    const url = `http://localhost:8080/monAn/delete/${maMonAn}`;
    fetch(url, { method: 'DELETE' })
      .then(() => {
        toast.success("Product deleted successfully");
        setProducts(products.filter(product => product.maMonAn !== maMonAn));
      })
      .catch(error => console.error(error));
  };

  const handleFormSubmit = (values) => {
    const url = "http://localhost:8080/monAn/create";
    fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(values)
    })
      .then(res => res.json())
      .then(data => {
        console.log(data)
        setProducts([...products, { ...data, key: data.maMonAn }]);
        toast.success("Product created successfully");
        setIsAddProductModalVisible(false);
      })
      .catch(error => console.error(error));
  };

  useEffect(() => {
    fetch("http://localhost:8080/monAn/all")
      .then(res => res.json())
      .then(data => {
        const updatedProducts = data.map(product => ({ ...product, key: product.maMonAn }));
        setProducts(updatedProducts);
        console.log(data)
      })
      .catch(error => console.error(error));
  }, []);

  const columns = [
    {
      title: 'Mã món ăn',
      dataIndex: 'maMonAn',
      key: 'maMonAn',
    },
    {
      title: 'Tên món ăn',
      dataIndex: 'tenMonAn',
      key: 'tenMonAn',
    },
    {
      title: 'Giá',
      dataIndex: 'gia',
      key: 'gia',
    },
    {
      title: 'Actions',
      key: 'actions',
      render: (values) => (
        <Space size="middle">
          <Tag key={`details-${values.maMonAn}`} color="geekblue" onClick={() => handleShowDetail(values.maMonAn)}>Details</Tag>
          <Tag key={`delete-${values.maMonAn}`} color="volcano" onClick={() => handleRemove(values.maMonAn)}>Delete</Tag>
        </Space>
      ),
    }
  ];

  return (
    <div>
      <div className="row align-items-center mb-3">
        <div className="col-sm">
          <h1>Product Management</h1>
        </div>
        <div className="col-sm-auto">
          <Button type="primary" onClick={handleAddProduct}>Add Product</Button>
        </div>
      </div>

      <Table columns={columns} dataSource={products} />

      <Modal
        title="Product Details"
        visible={isDetailModalVisible}
        onCancel={handleCloseDetailModal}
        footer={null}
        width={800}
      >
        {selectedProduct ? (
          <Descriptions bordered>
            <Descriptions.Item label="Mã món ăn">{selectedProduct.maMonAn}</Descriptions.Item>
            <Descriptions.Item label="Tên món ăn">{selectedProduct.tenMonAn}</Descriptions.Item>
            <Descriptions.Item label="Giá">{selectedProduct.gia}</Descriptions.Item>
            <Descriptions.Item label="Mô tả">{selectedProduct.moTa}</Descriptions.Item>
          </Descriptions>
        ) : (
          <p>No product selected.</p>
        )}
      </Modal>

      <Modal
        title="Add Product"
        visible={isAddProductModalVisible}
        onCancel={handleCloseAddProductModal}
        footer={null}
        width={800}
      >
        <CreateProductsForm onSubmit={handleFormSubmit} onClose={handleCloseAddProductModal} />
      </Modal>
    </div>
  );
};

export default ProductManagement;
