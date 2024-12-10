import React, { useState, useEffect } from 'react';
import { Table, Space, Tag, message, Spin } from 'antd';

const Notification = () => {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchNotifications = async () => {
    setLoading(true);
    const url = 'http://localhost:8080/thongBao/all';

    try {
      const response = await fetch(url);
      const data = await response.json();

      // Process notifications data
      const processedData = data.map((notification) => ({
        ...notification,
        thoiGian: notification.thoiGian.split('T')[0],
      }));

      console.log(processedData);
      setNotifications(processedData);
    } catch (error) {
      console.error('Error fetching notifications:', error);
      message.error('Error fetching notifications');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  const handleEdit = (maThongBao) => {
    // Implement in far far future
  };

  const handleDelete = (record) => {
    
  };

  const columns = [
    {
      title: 'Mã thông báo',
      dataIndex: 'maThongBao',
      key: 'maThongBao',
    },
    {
      title: 'Nội dung thông báo',
      dataIndex: 'noiDungThongBao',
      key: 'noiDungThongBao',
    },
    {
      title: 'Thời gian tạo',
      dataIndex: 'thoiGian',
      key: 'thoiGian',
    },
    {
      title: 'Action',
      key: 'action',
      render: (text, record) => (
        <Space size="middle">
          <Tag color="blue" onClick={() => handleEdit(record)} aria-label="Edit notification">
            Edit
          </Tag>
          <Tag color="red" onClick={() => handleDelete(record)} aria-label="Delete notification">
            Delete
          </Tag>
        </Space>
      ),
    },
  ];

  return (
    <div>
      <h1>Notification Management</h1>
      {loading ? (
        <div className="loading-spinner">
          <Spin size="large" />
        </div>
      ) : (
        <Table
          columns={columns}
          dataSource={notifications}
          rowKey="maThongBao"
        />
      )}
    </div>
  );
};

export default Notification;
