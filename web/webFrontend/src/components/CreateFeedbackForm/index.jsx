import React from 'react';
import { Form, Input, Button } from 'antd';

const CreateFeedbackForm = ({ onSubmit, onClose, maDon }) => {
  const [form] = Form.useForm();

  const handleFormSubmit = (values) => {
    console.log(values);
    // onSubmit(values);
    form.resetFields();
  };

  return (
    <div>
      {/* <Button className="close-button" onClick={onClose}>x</Button> */}
      <Form form={form} layout="vertical" onFinish={handleFormSubmit}>
        <Form.Item name="noiDung" label="Nội dung khiếu nại" rules={[{ required: true, message: 'Please enter the product name' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="maMonAn" label="Giá" rules={[{ required: true, message: 'Please enter the price' }]}>
            {maDon}
        </Form.Item>
        <Form.Item name="maThongTin">
        </Form.Item>
            {/* {localStorage.setItem('userdata')['cccd']} */}

        <Form.Item>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default CreateFeedbackForm;