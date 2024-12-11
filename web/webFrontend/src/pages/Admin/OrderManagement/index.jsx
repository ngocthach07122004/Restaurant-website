import { useEffect, useState } from "react";
import { Space, Table, Tag } from 'antd';
import { toast } from "react-toastify";
import { Button, Input, Formm } from "antd";
import { useNavigate } from 'react-router-dom';

const OrderManagement = () => {
    const [isReceived, setIsReceived] = useState(false);
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(false);
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
      title: 'Thoi gian dat',
      dataIndex: 'thoiGianDat',
      key: 'thoiGianDat',
    },
    {
      title: 'Tong gia tien',
      dataIndex: 'tongGiaTien',
      key: 'tongGiaTien',
    },
    {
      title: 'Tinh trang thanh toan',
      dataIndex: 'tinhTrangThanhToan',
      key: 'tinhTrangThanhToan',
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


    useEffect( () => {
        // Fetch users from API
        setLoading(true)
        const url = "http://localhost:8080/donMonAn/all"
        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json())
        .then(data => {
            setOrders(data);
            console.log(data); 
        })
      //  .then(data => setUsers(data))
        .catch(error => console.error('Error:', error))
        .finally(() => setLoading(false))
    }, [isReceived])


    return (
        <div>   
            <Table columns={columns} dataSource={orders} loading={loading}/>
        </div>
    )
}

export default OrderManagement;