import React from 'react';
import { Form, Input, Button } from 'antd';

const CreateProductsForm = ({ onSubmit, onClose }) => {
  const [form] = Form.useForm();

  const handleFormSubmit = (values) => {
    onSubmit(values);
    form.resetFields();
  };

  return (
    <div>
      {/* <Button className="close-button" onClick={onClose}>x</Button> */}
      <Form form={form} layout="vertical" onFinish={handleFormSubmit}>
        <Form.Item name="tenMonAn" label="Tên món ăn" rules={[{ required: true, message: 'Please enter the product name' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="gia" label="Giá" rules={[{ required: true, message: 'Please enter the price' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="moTa" label="Mô tả" rules={[{ required: true, message: 'Please enter the description' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="loaiMonAn" label="Loại món ăn" rules={[{ required: true, message: 'Please enter the category' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="anhMonAn" label="Ảnh món ăn" rules={[{ required: true, message: 'Please enter the image URL' }]}>
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

export default CreateProductsForm;