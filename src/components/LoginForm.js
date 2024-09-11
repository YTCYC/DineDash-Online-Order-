import { Button, Form, Input, message } from "antd";
import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { login } from "../utils";

// fill out login information and hit login button
// calls backend and fetch data
// receives data from backend and process and render

// practice class component
class LoginForm extends React.Component {
  state = {
    loading: false,
  };

  onFinish = (data) => {
    this.setState({
      loading: true,
    });
    // when run login, it will run fetch function defined in utils
    login(data)
      // this data is passed by response from utils -> login function
      .then(() => {
        // if undefined
        message.success(`Login Successful`);
        this.props.onSuccess();
      })
      .catch((err) => {
        // if throw error
        message.error(err.message);
      })
      .finally(() => {
        this.setState({
          loading: false,
        });
      });
  };

  render = () => {
    return (
      <Form
        name="normal_login"
        // when click Login it will run onFinish
        onFinish={this.onFinish}
        style={{
          width: 300,
          margin: "auto",
        }}
      >
        <Form.Item
          name="username"
          rules={[{ required: true, message: "Please input your Username!" }]}
        >
          <Input prefix={<UserOutlined />} placeholder="Username" />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: "Please input your Password!" }]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="Password" />
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit" loading={this.state.loading}>
            Login
          </Button>
        </Form.Item>
      </Form>
    );
  };
}

export default LoginForm;
