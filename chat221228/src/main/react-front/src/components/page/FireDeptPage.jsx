import React, { useEffect, useState } from "react";
import { Table } from "react-bootstrap";
import Bottom from "../include/Bottom";
import Header from "../include/Header";
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.17.1/firebase-app.js";
import {
  getDatabase,
  ref,
  set,
  onValue,
} from "https://www.gstatic.com/firebasejs/9.17.1/firebase-database.js";
import DeptRow from "../../dept/DeptRow";

const firebaseConfig = {
  apiKey: process.env.REACT_APP_FS_APIKEY,
  authDomain: process.env.REACT_APP_FS_AUTHDOMAIN,
  databaseURL: process.env.REACT_APP_FS_DATABASEURL,
  projectId: process.env.REACT_APP_FS_PROJECTID,
  storageBucket: process.env.REACT_APP_FS_STORAGEBUCKET,
  messagingSenderId: process.env.REACT_APP_FS_MESSAGINGSENDERID,
  appId: process.env.REACT_APP_FS_APPID,
};

const app = initializeApp(firebaseConfig);
console.log(app);
const database = getDatabase();

const FireDeptPage = () => {
  const [depts, setDepts] = useState([
    { deptno: 10, dname: "개발1팀", loc: "부산" },
    { deptno: 20, dname: "개발2팀", loc: "서울" },
    { deptno: 30, dname: "운영팀", loc: "대전" },
  ]);
  useEffect(() => {
    //setDepts(depts);
    console.log(database);
    console.log(depts);
    const starCountRef = ref(database, "dept");
    onValue(starCountRef, (snapshot) => {
      const data = snapshot.val();
      console.log(data);
      setDepts(data);
    });
  }, []); //옵션에 별도의 값을 지정하지 않으면 최조 한번만 실행됨
  //useEffect에서 초기화 된 상태값 출력해보기
  console.log(depts);
  return (
    <>
      <Header />
      <div>부서관리 페이지</div>
      <div className="dept-list">
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>부서번호</th>
              <th>부서명</th>
              <th>지역</th>
            </tr>
          </thead>
          <tbody>
            {Object.keys(depts).map((key) => (
              <DeptRow key={key} dept={depts[key]} />
            ))}
          </tbody>
        </Table>
      </div>
      <Bottom />
    </>
  );
};

export default FireDeptPage;
