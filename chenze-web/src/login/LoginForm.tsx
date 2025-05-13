import React, {useState} from 'react';
import {Tabs, Form, Input, Button, QRCode, message} from 'antd';

const LoginForm: React.FC = () => {
    const [activeTab, setActiveTab] = useState('account');

    const onFinish = (values: any) => {
        console.log('登录信息:', values);
        message.success('登录成功');
    };

    const items = [
        {
            key: 'account',
            label: '账号密码登录',
            children: (
                <Form
                    name="loginForm"
                    onFinish={onFinish}
                    style={{maxWidth: 300, margin: '0 auto'}}
                >
                    <Form.Item
                        name="username"
                        rules={[{required: true, message: '请输入用户名！'}]}
                    >
                        <Input placeholder="用户名"/>
                    </Form.Item>

                    <Form.Item
                        name="password"
                        rules={[{required: true, message: '请输入密码！'}]}
                    >
                        <Input.Password placeholder="密码"/>
                    </Form.Item>

                    <Form.Item>
                        <Button type="primary" htmlType="submit" block>
                            登录
                        </Button>
                    </Form.Item>
                </Form>
            ),
        },
        {
            key: 'qrcode',
            label: '扫码登录',
            children: (
                <div style={{
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    justifyContent: 'center',
                }}>
                    <QRCode value="https://example.com/login/scan"/>
                    <p style={{marginTop: 16}}>请使用手机扫码登录</p>
                </div>
            ),
        },
    ];

    return (
        <div style={{maxWidth: 400, margin: '100px auto', padding: 24, border: '1px solid #eee', borderRadius: 8}}>
            <h2 style={{textAlign: 'center'}}>登录</h2>
            <Tabs
                defaultActiveKey="account"
                activeKey={activeTab}
                onChange={setActiveTab}
                items={items}
            />
        </div>
    );
};

export default LoginForm;