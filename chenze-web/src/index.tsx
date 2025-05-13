import React from 'react';
import ReactDOM from 'react-dom/client';
import 'antd/dist/reset.css'; // AntD v5 推荐样式引入
import './index.css';
import App from './App';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

