import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Space, Table, Tag, Modal, Button, Descriptions } from 'antd';
import CreateFeedbackForm from '../../components/CreateFeedbackForm/index';

const Profile = () => {
  const [userData, setUserData] = useState({});
  const [orders, setOrders] = useState([]);
  const [isReceived, setIsReceived] = useState(false);
  const [receiveData, setReceiveData] = useState(false);
  const [maDonToFeedBack, setMaDonToFeedBack] = useState(null);
  const navigate = useNavigate();
  const [isAddFeedbackModalVisible, setIsAddFeedbackModalVisible] = useState(false);
  
  // Edit Modal
  const [isEditModalVisible, setIsEditModalVisible] = useState(false);
  const [editFormData, setEditFormData] = useState({});

  const showEditModal = () => {
    setEditFormData(userData); // Pre-fill form with current user data
    setIsEditModalVisible(true);
  };
  
  const closeEditModal = () => {
    setIsEditModalVisible(false);
  };

  const handleEditFormSubmit = async () => {
    try {
      const url = `http://localhost:8080/thongTin/update/${userData.cccd}`;
      const response = await fetch(url, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(editFormData),
      });
      if (response.ok) {
        // Since the response is plain text, use response.text()
        const message = await response.text();
        console.log(message); // Should log "update success"
  
        // Optionally, you might want to refresh the user data
        const userResponse = await fetch(`http://localhost:8080/thongTin/${userData.cccd}`);
        const updatedUserData = await userResponse.json();
        setUserData(updatedUserData); // Update user data state
        setIsEditModalVisible(false); // Close modal
      } else {
        console.error('Error updating user data:', response.statusText);
      }
    } catch (error) {
      console.error('Error updating user data:', error);
    }
  };
  // End Edit Modal

  const handleCloseAddFeedbackModal = () => {
    setIsAddFeedbackModalVisible(false);
  };
  const handleAddFeedback = (id) => {
    setMaDonToFeedBack(id);
    console.log(id);
    console.log(localStorage.getItem('cccd'));
    // setIsAddFeedbackModalVisible(true);
  };

  const handleFormSubmit = (values) => {
    const url = "http://localhost:8080/donKhieuNai/create";
    fetch(url, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(values)
    })
      .then(res => res.json())
      .then(data => {console.log(data);})
      .catch(error => console.error(error));
  };



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
        setOrders(data);
        console.log(data); 
    })
//    .then(data => setUsers(data))
    .catch(error => console.error('Error:', error))
}, [isReceived])

  useEffect(() => {
    const cccd = localStorage.getItem('cccd');
    const url = `http://localhost:8080/thongTin/${cccd}`;
    fetch(url, {
      method: "GET",
    })
      .then((res) => res.json())
      .then((data) => {
        setUserData(data);
        setReceiveData(true);
        console.log(cccd);
      })
      .catch((err) => console.log(err));
  }, [receiveData]);

  const columns = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
    },
    {
        title: "Date",
        dataIndex: "orderDate",
        key: "orderDate",
        render: (text) => {return text},
    },
    {
      title: "Total",
      dataIndex: "totalPrice",
      key: "totalPrice",
      render: (text) => {return text + "VND"} ,
    },
    {
      title: "Payment method",
      dataIndex: "paymentMethod",
      key: "paymentMethod",
    },
    {
      title: "Status",
      dataIndex: "status",
      key: "status",
      render: (text) => {
        if(text === 'pending')
          return <Tag color={"orange"}>Pending</Tag>;
        return <Tag color={"green"}>Done</Tag>;
      },
    },
    {
      title: "Action",
      dataIndex: "id", // Ensure `id` is the field containing the unique identifier
      key: "action",
      render: (id) => (
        <Tag color={"blue"} onClick={() => handleAddFeedback(id)}>
          Khiếu nại
        </Tag>
      ),
    },
  ];

  const data = [
    {
      id: '1',
      orderDate: '2017-01-01',
      totalPrice: 10000,
      paymentMethod: 'Credit Card',
      status: 'pending',
    }
  ];

  return (
    <div>
      <div className="container-feedback">
        <section style={{ backgroundColor: "#eee" }}>
          <div className="container py-5">
            <div className="d-flex justify-content-center">
                <div className="card" style={{ flex: "1", maxWidth: "33.33%" }}>
                  <div className="card-body text-center align-items-center">
                    <div className="flex justify-center">
                      <img
                        src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                        alt="avatar"
                        className="rounded-circle img-fluid"
                        style={{ width: "150px" }}
                      />
                    </div>
                    <h5 className="my-3">{userData.ten?userData.ten:"nah"}</h5>
                    <p className="text-muted mb-1">
                      {userData.is_staff
                        ? "Signed in as an ADMIN"
                        : "Signed in as a CUSTOMER"}
                    </p>
                    <p className="text-muted mb-4">{userData.email}</p>
                    <div className="d-flex justify-content-center mb-2">
                      <button onClick={showEditModal} type="button" className="btn btn-primary">
                        Edit
                      </button>
                    </div>
                  </div>
                </div>

                <div className="card" style={{ flex: "1", maxWidth: "66.67%" }}>
                  <div className="card-body">
                    <div className="row">
                      <div className="col-sm-3">
                        <p className="mb-0">Full Name</p>
                      </div>
                      <div className="col-sm-9">
                        <p className="text-muted mb-0">{userData.ho} {userData.ten}</p>
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="col-sm-3">
                        <p className="mb-0">Email</p>
                      </div>
                      <div className="col-sm-9">
                        <p className="text-muted mb-0">{userData.email}</p>
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="col-sm-3">
                        <p className="mb-0">Phone</p>
                      </div>
                      <div className="col-sm-9">
                        <p className="text-muted mb-0">
                          {userData.phone_number
                            ? userData.phone_number
                            : "(+82) 123 456 789"}
                        </p>
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="col-sm-3">
                        <p className="mb-0">Balance</p>
                      </div>
                      <div className="col-sm-9">
                        <p className="text-muted mb-0">{userData.balance}</p>
                      </div>
                    </div>
                    <hr />
                    <div className="row">
                      <div className="col-sm-3">
                        <p className="mb-0">Money has spent</p>
                      </div>
                      <div className="col-sm-9">
                        <p className="text-muted mb-0">
                          {userData.money_spent}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                
            </div>
              <Table className="w-full lg:w-3/5" columns={columns} dataSource={data} />
          </div>
        </section>
      </div>

      <Modal
        title="Add Product"
        visible={isAddFeedbackModalVisible}
        onCancel={handleCloseAddFeedbackModal}
        footer={null}
        width={800}
      >
        <CreateFeedbackForm onSubmit={handleFormSubmit} onClose={handleCloseAddFeedbackModal} maDon={maDonToFeedBack}/>
      </Modal>

      <Modal
        title="Edit Profile"
        visible={isEditModalVisible}
        onCancel={closeEditModal}
        footer={[
          <Button key="back" onClick={closeEditModal}>
            Cancel
          </Button>,
          <Button key="submit" type="primary" onClick={handleEditFormSubmit}>
            Save
          </Button>,
        ]}
      >
        {/* Form Fields */}
        <form>
          <div className="form-group">
            <label>First Name</label>
            <input
              type="text"
              className="form-control"
              value={editFormData.ho}
              onChange={(e) => setEditFormData({ ...editFormData, ho: e.target.value })}
            />
          </div>
          <div className="form-group">
            <label>Last Name</label>
            <input
              type="text"
              className="form-control"
              value={editFormData.ten}
              onChange={(e) => setEditFormData({ ...editFormData, ten: e.target.value })}
            />
          </div>
          <div className="form-group">
            <label>Birth Date</label>
            <input
              type="date"
              className="form-control"
              value={editFormData.ngaySinh}
              onChange={(e) => setEditFormData({ ...editFormData, ngaySinh: e.target.value })}
            />
          </div>
          <div className="form-group">
            <label>Username</label>
            <input
              type="text"
              className="form-control"
              value={editFormData.tenDangNhap}
              onChange={(e) => setEditFormData({ ...editFormData, tenDangNhap: e.target.value })}
            />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              className="form-control"
              value={editFormData.email}
              onChange={(e) => setEditFormData({ ...editFormData, email: e.target.value })}
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              className="form-control"
              value={editFormData.matKhau}
              onChange={(e) => setEditFormData({ ...editFormData, matKhau: e.target.value })}
            />
            {editFormData.matKhau && editFormData.matKhau.length <= 8 && (
              <small className="text-danger">Password must be greater than 8 characters.</small>
            )}
          </div>
          <div className="form-group">
            <label>Confirm Password</label>
            <input
              type="password"
              className="form-control"
              value={editFormData.confirmPassword}
              onChange={(e) => setEditFormData({ ...editFormData, confirmPassword: e.target.value })}
            />
            {editFormData.confirmPassword && editFormData.confirmPassword !== editFormData.matKhau && (
              <small className="text-danger">Passwords do not match.</small>
            )}
          </div>
          <div className="form-group">
            <label>Sex</label>
            <select
              className="form-control"
              value={editFormData.gioiTinh}
              onChange={(e) => setEditFormData({ ...editFormData, gioiTinh: e.target.value })}
            >
              <option value="">Select Sex</option>
              <option value="M">Male</option>
              <option value="F">Female</option>
            </select>
            {editFormData.gioiTinh === '' && <small className="text-danger">Please select your sex.</small>}
          </div>
          {/* Include other fields as necessary */}
        </form>
      </Modal>
    </div>
  );
};

export default Profile;
