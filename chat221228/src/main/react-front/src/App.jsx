import { Route, Routes } from "react-router-dom";
import "./App.css";
import DeptPage from "./components/page/DeptPage";
import HomePage from "./components/page/HomePage";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" exact={true} element={HomePage} />
        <Route path="/dept/:id" exact={true} element={<DeptPage />} />
      </Routes>
    </>
  );
}

export default App;
