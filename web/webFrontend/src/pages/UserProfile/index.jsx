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
            <div className="row">
                <div className="card mb-4">
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
                      <button type="button" className="btn btn-primary">
                        Edit
                      </button>
                      <button
                        type="button"
                        onClick={() => navigate("/student/printing_history")}
                        className="btn btn-outline-primary ms-1"
                      >
                        View printing log
                      </button>
                    </div>
                  </div>
                </div>

                <div className="card mb-8">
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
              <Table className="w-full lg:w-3/5" columns={columns} dataSource={data} />


                {/* <div className="row">
                  <div className="col-md-12">
                    <div className="card mb-10 flex">
                      <div className="card-body">
                        <ul className="list-group list-group-flush">
                          {orderLog
                            .sort(
                              (a, b) =>
                                new Date(b.timer_start) -
                                new Date(a.timer_start)
                            ) // Sort in descending order by purchase_time
                            .slice(0, 4) // Take the 4 most recent logs
                            .map((log) => (
                              <li className="list-group-item d-flex justify-content-between align-items-center p-3">
                                <div>
                                  <strong>
                                    <span className="text-red-500 text-2xl">
                                      {-log.page_cost}
                                    </span>
                                  </strong>{" "}
                                  {log.status === "pending" ? (
                                    <strong className="text-yellow-600">
                                      [ON PENDING]
                                    </strong>
                                  ) : (
                                    <strong className="text-green-600">
                                      [DONE]
                                    </strong>
                                  )}
                                  <span>
                                    [
                                    {new Date(log.timer_start).toLocaleString()}
                                    ]
                                  </span>
                                  <br />
                                  <strong>Order's name:</strong>{" "}
                                  {log.order_name} <br />
                                </div>
                              </li>
                            ))}
                        </ul>
                      </div>
                    </div>
                  </div>
                </div> */}
            </div>
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
    </div>
  );
};

export default Profile;
