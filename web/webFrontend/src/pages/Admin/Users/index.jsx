import { useEffect, useState } from "react";
import { Space, Table, Tag } from 'antd';
import { toast } from "react-toastify";
import { Button, Input, Form } from "antd";
import { useNavigate } from 'react-router-dom';

const Users = () => {
    const [isReceived, setIsReceived] = useState(false);
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();
    const handleShowDetail = (id) => navigate(`${id}`);
    const columns = [
    {
      title: 'CCCD',
      dataIndex: 'cccd',
      key: 'cccd',
      // render: (text) => <a>{text}</a>,
    },
    {
      title: 'Ten dang nhap',
      dataIndex: 'tenDangNhap',
      key: 'tenDangNhap',
    },
    {
      title: 'Gioi Tinh',
      dataIndex: 'gioiTinh',
      key: 'gioiTinh',
    },
    {
      title: 'So dien thoai',
      dataIndex: 'soDienThoai',
      key: 'soDienThoai',
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
            <div className='cursor-pointer' onClick={() => {handleShowDetail(values.cccd)}}>Details</div>
          </Tag>
        </Space>
      ),
    },
  ];

    useEffect(() => {
        // Fetch users from API
        const url = "http://localhost:8080/thongTin/all"
        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(res => res.json())
        .then(data => {
            setUsers(data);
            console.log(data); 
        })
    //    .then(data => setUsers(data))
        .catch(error => console.error('Error:', error))
    }, [isReceived])

    return (
        <div>   
            hi mom, this is user UserManagement
            <Table columns={columns} dataSource={users} />
        </div>
    )
}

export default Users;