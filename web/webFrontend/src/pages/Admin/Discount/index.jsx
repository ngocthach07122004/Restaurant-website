import React, { useEffect, useState } from "react";
import { Space, Table, Tag, Modal, Button, Descriptions } from "antd";
import { toast } from "react-toastify";
import CreateDiscountForm from "../../../components/AdminComponent/CreateDiscountForm/index"

const Discount = () => {
  const [discounts, setDiscounts] = useState([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedDiscount, setSelectedDiscount] = useState(null);
  const [isAddDiscountModalVisible, setIsAddDiscountModalVisible] = useState(false);
  const handleAddDiscount = () => {
    setIsModalVisible(true);
  };

  const handleFormSubmit = () => {
    return;
  }

  const handleCloseAddDiscountModal = () => {
    setIsAddDiscountModalVisible(false);
  };

  // const handleCloseDetailModal = () => {
  //   setIsModalVisible(false);
  //   setSelectedDiscount(null);
  // };

  const handleRemove = (maMonAn) => {
    const url = `http://localhost:8080/monAn/delete/${maMonAn}`;
    fetch(url, { method: 'DELETE' })
      .then(() => {
        toast.success("Product deleted successfully");
        setDiscounts(discounts.filter(discount => discount.maMonAn !== maMonAn));
      })
      .catch(error => console.error(error));
  };

  useEffect(() => {
    fetch("http://localhost:8080/maKhuyenMai/all")
      .then((res) => res.json())
      .then((data) => {
        const updatedDiscounts = data.map((discount) => ({ ...discount, key: discount.idKhuyenMai }));
        setDiscounts(updatedDiscounts);
        console.log(data);
      })
      .catch((error) => console.error(error));
  }, []);

  const columns = [
    {
      title: "Mã khuyến mãi",
      dataIndex: "idKhuyenMai",
      key: "idKhuyenMai",
    },
    {
      title: "Mô tả",
      dataIndex: "moTa",
      key: "moTa",
    },
    {
      title: "Giá trị giảm giá",
      dataIndex: "giaTriGiamGia",
      key: "giaTriGiamGia",
    },
    {
      title: "Actions",
      key: "actions",
      render: (values) => (
        <Space size="middle">
          <Tag color="volcano" onClick={() => handleRemove(values.idKhuyenMai)}>Delete</Tag>
        </Space>
      ),
    },
  ];

  return (
    <div>
      <div className="row align-items-center mb-3">
        <div className="col-sm">
          <h1>Discounts</h1>
        </div>
        <div className="col-sm-auto">
          <Button type="primary" onClick={handleAddDiscount}>Add Discount</Button>
        </div>
      </div>

      <Table columns={columns} dataSource={discounts} />
      <Modal
        title="Add Discount"
        visible={isAddDiscountModalVisible}
        onCancel={handleCloseAddDiscountModal}
        footer={null}
        width={800}
      >
        <CreateDiscountForm onSubmit={handleFormSubmit} onClose={handleCloseAddDiscountModal} />
      </Modal>
    </div>
  );
};

export default Discount;
