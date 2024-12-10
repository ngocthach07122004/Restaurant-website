import React, { useState, useEffect } from "react";
import { Table, Select, Space, Tag } from "antd";

const { Option } = Select;

const Employee = () => {
  const [employeeType, setEmployeeType] = useState("all");
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchEmployees = (type) => {
    setLoading(true);
    let url = "http://localhost:8080/nhanvien/all";
    if (type !== "all") {
      url = `http://localhost:8080/${type}/all`;
    }
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setEmployees(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching employees:", error);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchEmployees(employeeType);
  }, [employeeType]);

  const handleEmployeeTypeChange = (value) => {
    setEmployeeType(value);
  };

  const columns = [
    {
      title: "CCCD",
      dataIndex: ["cccd", "cccd"],
      key: "cccd.cccd",
    },
    {
      title: "Tên",
      dataIndex: ["cccd", "ten"],
      key: "ten",
    },
    {
      title: "Ngày vào làm",
      dataIndex: "ngayVaoLam",
      key: "ngayVaoLam",
    },
    {
      title: "Lương",
      dataIndex: "luong",
      key: "luong",
    },
    {
      title: "Action",
      key: "action",
      render: (text, record) => (
        <Space size="middle">
          <Tag color="blue">Edit</Tag>
          <Tag color="red">Delete</Tag>
        </Space>
      ),
    },
  ];

  return (
    <div>
      <h1>Employee Management</h1>
      <Select
        defaultValue="all"
        style={{ width: 200, marginBottom: 20 }}
        onChange={handleEmployeeTypeChange}
      >
        <Option value="all">All Employees</Option>
        <Option value="nhanvienquanly">Nhân Viên Quản Lý</Option>
        <Option value="nhanviengiaohang">Nhân Viên Giao Hàng</Option>
        <Option value="nhanvienthungan">Nhân Viên Thu Ngân</Option>
        <Option value="nhanvienphucvu">Nhân Viên Phục Vụ</Option>
        <Option value="nhanvientongdai">Nhân Viên Tổng Đài</Option>
      </Select>
      <Table
        columns={columns}
        dataSource={employees}
        loading={loading}
        rowKey="cccd.cccd"
      />
    </div>
  );
};

export default Employee;
