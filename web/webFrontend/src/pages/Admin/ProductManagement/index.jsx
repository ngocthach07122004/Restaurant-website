import { useEffect, useState } from "react";
import { Space, Table, Tag } from 'antd';
import { toast } from "react-toastify";
import { Button, Input, Form, Descriptions, Modal } from "antd";
import { useNavigate } from 'react-router-dom';

const ProductManagement = () => {
    const [isReceived, setIsReceived] = useState(false);
    const [products, setProducts] = useState([]);
    const navigate = useNavigate();
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [selectedProduct, setSelectedProduct] = useState(null); // For storing the selected user

    const handleShowDetail = (maMonAn) => {
        // Find the selected user from the current list
        const product = products.find((u) => u.maMonAn === maMonAn);
        setSelectedProduct(product); // Set selected user details
        setIsModalVisible(true); // Show modal
    };

    const handleCloseModal = () => {
        setIsModalVisible(false);
        setSelectedProduct(null); // Clear selected user
    };
    
    const handleRemove = (maMonAn) => {
      // Call the API to remove the selected user
      const url = `http://localhost:8080/monAn/delete/${maMonAn}`;
      fetch(url, {
          method: 'DELETE',
          headers: {
              'Content-Type': 'application/json'
          }
      })
     .then(res => res.json())
     .catch(error => console.log(error));
     setIsReceived(false);
    }

    const columns = [
    {
      title: 'Ma mon an',
      dataIndex: 'maMonAn',
      key: 'maMonAn',
      // render: (text) => <a>{text}</a>,
    },
    {
      title: 'Ten mon an',
      dataIndex: 'tenMonAn',
      key: 'tenMonAn',
    },
    {
      title: 'Gia',
      dataIndex: 'gia',
      key: 'gia',
    },
    // {
    //   title: 'Mo ta',
    //   dataIndex: 'moTa',
    //   key: 'moTa',
    // },
    // {
    //   title: 'Quan Tri Vien',
    //   key: 'cccdQuanTriVien',
    //   dataIndex: 'cccdQuanTriVien',
    //   render: (_, { tags }) => (
    //     <>
    //       {tags.map((tag) => {
    //         let color = tag === false ? 'volcano' : 'green';
    //         return (
    //           <Tag color={color} key={tag}>
    //             {/* {tag.toUpperCase()} */}
    //             isPaid
    //           </Tag>
    //         );
    //       })}
    //     </>
    //   ),
    // },
    {
      title: 'Action',
      dataIndex: null,
      render: (values) => (
        <Space size="middle">
          <Tag color={'geekblue'}>
            <div className='cursor-pointer' onClick={() => {handleShowDetail(values.maMonAn)}}>Details</div>
          </Tag>
          <Tag color={'volcano'}>
            <div className='cursor-pointer' onClick={() => {handleRemove(values.maMonAn)}}>Delete</div>
          </Tag>
        </Space>
      ),
    },
  ];


    useEffect(() => {
        // Fetch users from API
        const url = "http://localhost:8080/monAn/all"
        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json())
        .then(data => {
            setProducts(data);
            console.log(data); 
        })
    //    .then(data => setUsers(data))
        .catch(error => console.error('Error:', error))
        setIsReceived(true);
    }, [isReceived])


    return (
        <div>   
            hi mom, this is products management page
            <Table columns={columns} dataSource={products} />
            <Modal
                title="User Details"
                visible={isModalVisible}
                onCancel={handleCloseModal}
                footer={null}
                width={800}
            >
                {selectedProduct ? (
                    <Descriptions bordered>
                        <Descriptions.Item label="Ma mon an">{selectedProduct.maMonAn}</Descriptions.Item>
                        <Descriptions.Item label="Ten mon an">{selectedProduct.tenMonAn}</Descriptions.Item>
                        <Descriptions.Item label="Gia mon an">{selectedProduct.gia}</Descriptions.Item>
                        <Descriptions.Item label="Mo ta mon an">{selectedProduct.moTa}</Descriptions.Item>
                    </Descriptions>
                ) : (
                    <p>No user selected.</p>
                )}
            </Modal>
        </div>
    )
}

export default ProductManagement;