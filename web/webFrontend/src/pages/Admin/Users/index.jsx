import { useEffect, useState } from "react";
import { Space, Table, Tag } from "antd";
import { toast } from "react-toastify";
import { Button, Input, Form, Modal, Descriptions } from "antd";
import { useNavigate } from "react-router-dom";

const Users = () => {
  const [isReceived, setIsReceived] = useState(false);
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null); // For storing the selected user

  const handleShowDetail = (cccd) => {
    // Find the selected user from the current list
    const user = users.find((u) => u.cccd === cccd);
    setSelectedUser(user); // Set selected user details
    setIsModalVisible(true); // Show modal
  };

  const handleCloseModal = () => {
    setIsModalVisible(false);
    setSelectedUser(null); // Clear selected user
  };
  const handleRemove = (cccd) => {
    // Call the API to remove the selected user
    const url = `http://localhost:8080/thongTin/delete/${cccd}`;
    fetch(url, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((res) => res.json())
    .catch((error) => console.log(error));
    setIsReceived(false);
  };

  const columns = [
    {
      title: "CCCD",
      dataIndex: "cccd",
      key: "cccd",
      // render: (text) => <a>{text}</a>,
    },
    {
      title: "Ten dang nhap",
      dataIndex: "tenDangNhap",
      key: "tenDangNhap",
    },
    {
      title: "Gioi Tinh",
      dataIndex: "gioiTinh",
      key: "gioiTinh",
    },
    {
      title: "So dien thoai",
      dataIndex: "soDienThoai",
      key: "soDienThoai",
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
      title: "Action",
      dataIndex: null,
      render: (values) => (
        <Space size="middle">
          <Tag color={"geekblue"}>
            <div className="cursor-pointer" onClick={() => { handleShowDetail(values.cccd);}}>Details</div>
          </Tag>
          <Tag color={'volcano'}>
            <div className='cursor-pointer' onClick={() => {handleRemove(values.cccd)}}>Delete</div>
          </Tag>
        </Space>
      ),
    },
  ];

  useEffect(() => {
    // Fetch users from API
    const url = "http://localhost:8080/thongTin/all";
    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setUsers(data);
        console.log(data);
      })
      //    .then(data => setUsers(data))
      .catch((error) => console.error("Error:", error));
      setIsReceived(true);
  }, [isReceived]);

  return (
    <div>
      hi mom, this is user UserManagement
      <Table columns={columns} dataSource={users} />
      <Modal
        title="User Details"
        visible={isModalVisible}
        onCancel={handleCloseModal}
        footer={null}
        width={800}
      >
        {selectedUser ? (
          <Descriptions bordered>
            <Descriptions.Item label="CCCD">
              {selectedUser.cccd}
            </Descriptions.Item>
            {/* {selectedUser.cccdQuanTriVien} && <Descriptions.Item label="CCCD cua Quan Tri Vien">{selectedUser.cccdQuanTriVien}</Descriptions.Item> */}

            <Descriptions.Item label="Tên đăng nhập">
              {selectedUser.tenDangNhap}
            </Descriptions.Item>
            <Descriptions.Item label="Giới Tính">
              {selectedUser.gioiTinh}
            </Descriptions.Item>
            <Descriptions.Item label="Số điện thoại">
              {selectedUser.soDienThoai}
            </Descriptions.Item>
            <Descriptions.Item label="Ho va ten">
              {selectedUser.ho} {selectedUser.ten}
            </Descriptions.Item>
            <Descriptions.Item label="Ngay sinh">
              {selectedUser.ngaySinh}
            </Descriptions.Item>
          </Descriptions>
        ) : (
          <p>No user selected.</p>
        )}
      </Modal>
    </div>
  );
};

export default Users;
