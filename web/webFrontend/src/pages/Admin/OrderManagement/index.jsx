import { useEffect, useState } from "react";
import { Space, Table, Tag } from 'antd';
import { toast } from "react-toastify";
import { Button, Input, Form } from "antd";
import { useNavigate } from 'react-router-dom';

const OrderManagement = () => {
    const [isReceived, setIsReceived] = useState(false);
    const [orders, setOrders] = useState([]);
    const navigate = useNavigate();
    const handleShowDetail = (id) => navigate(`${id}`);

    const columns = [
    {
      title: 'Ma don mon',
      dataIndex: 'maDon',
      key: 'maDon',
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
    {
      title: 'Mo ta',
      dataIndex: 'moTa',
      key: 'moTa',
    },
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
        </Space>
      ),
    },
  ];


    useEffect(() => {
        // Fetch users from API
        const url = "http://localhost:8080/donMonAn/all"
        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json())
        .then(data => {
            // setOrders(data);
            console.log(data); 
        })
    //    .then(data => setUsers(data))
        .catch(error => console.error('Error:', error))
    }, [isReceived])


    return (
        <div>   
            <Table columns={columns} dataSource={orders} />
        </div>
    )
}

export default OrderManagement;