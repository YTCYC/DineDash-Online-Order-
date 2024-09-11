import React from "react";
// 'react' is what we added in package.json
import ReactDOM from "react-dom/client";
// import ReactDOM from 'react-dom'; this is older version
// . means current folder
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";

// index.js defines entry point, which is App
// we render main component App at root location in html
// later we add other components to App so they can be rendered
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// ReactDOM.render(<App />, getElementByID("root")) 老的写法 和上面写法一样

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
// recorde some user activity data
