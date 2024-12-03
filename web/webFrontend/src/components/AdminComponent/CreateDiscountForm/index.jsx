import React from 'react';
import { Form, Input, Button } from 'antd';

const CreateDiscountForm = ({ onSubmit, onClose }) => {
  const [form] = Form.useForm();

  const handleFormSubmit = (values) => {
    onSubmit(values);
    form.resetFields();
  };

  return (
    <div>
      {/* <Button className="close-button" onClick={onClose}>x</Button> */}
      <Form form={form} layout="vertical" onFinish={handleFormSubmit}>
        <Form.Item name="dieuKienDung" label="Điều kiện dùng (có thể để trống)">
          <Input />
        </Form.Item>
        <Form.Item name="giaTriGiamGia" label="Giá trị giảm giá" rules={[{ required: true, message: 'Please enter the description' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="moTa" label="Mô tả" rules={[{ required: true, message: 'Please enter the description' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="maChiNhanh" label="Mã Chi Nhánh" rules={[{ required: true, message: 'Please enter the description' }]}>
          <Input />
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default CreateDiscountForm;