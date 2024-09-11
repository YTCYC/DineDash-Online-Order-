import { Layout, Typography } from "antd";
import { useState } from "react";
import FoodList from "./components/FoodList";
import LoginForm from "./components/LoginForm";
import MyCart from "./components/MyCart";
import SignupForm from "./components/SignupForm";

const { Header, Content } = Layout;
const { Title } = Typography;

function App() {
  const [authed, setAuthed] = useState(false);

  return (
    <Layout style={{ height: "200vh" }}>
      <Header>
        <div
          className="header"
          style={{ display: "flex", justifyContent: "space-between" }}
        >
          <Title
            level={1}
            style={{ color: "cyan", lineHeight: "inherit", marginBottom: 0 }}
          >
            LaoTao Food
          </Title>
          <div>{authed ? <MyCart /> : <SignupForm />}</div>
        </div>
      </Header>
      <Content
        style={{
          padding: "50px",
          maxHeight: "calc(100% - 64px)",
          overflowY: "auto",
        }}
      >
        {authed ? (
          <FoodList />
        ) : (
          <LoginForm onSuccess={() => setAuthed(true)} />
        )}
      </Content>
    </Layout>
  );
}

export default App;
// need to enanble export so that we can import this into other file
