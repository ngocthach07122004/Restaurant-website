import React from 'react';
import { Form, Input, Button, Select } from 'antd';

const CreateProductsForm = ({ onSubmit, onClose }) => {
  const [form] = Form.useForm();

  const handleFormSubmit = (values) => {
    onSubmit(values);
    form.resetFields();
  };

  const categories = ["Mon Nuoc", "Mon Man", "Mon Chay", "Nuoc Uong"];

  return (
    <div>
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
          <Select>
            {categories.map((category) => (
              <Select.Option key={category} value={category}>
                {category}
              </Select.Option>
            ))}
          </Select>
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