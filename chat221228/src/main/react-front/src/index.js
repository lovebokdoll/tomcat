import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { BrowserRouter } from "react-router-dom";
import AuthLogic from "./service/authLogic";
import firebaseApp from "./service/firebase";
import "@fortawesome/fontawesome-free/js/all.js";
import SampleApp from "./SampleApp";
import App from "./App";
//공통코드 ->service->authLogic.js ->import외부 js사용가능함
//브라우저에서 import하려면 반드시 babel필요함
const authLogic = new AuthLogic(firebaseApp); //인스턴스화(파라미터가 올 수 있다.)
//왜 파라미터가 필요한가?-firebaseApp->import firebaseApp from"./service/firebase";->export default firebaseApp
//authLogic.파이어베이스에서 제공하는 함수를 호출하겠다.
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <>
    <BrowserRouter>
      <App authLogic={authLogic} />
    </BrowserRouter>
  </>
);
