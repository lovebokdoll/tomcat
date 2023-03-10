import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Bottom from "../include/Bottom";
import Header from "../include/Header";
const EmpPage = ({ authLogic }) => {
  const navigate = useNavigate();
  const onLogout = () => {
    authLogic.logout();
  };
  useEffect(() => {
    authLogic.onAuthChange((user) => {
      if (!user) {
        navigate("/");
      }
    });
  });
  return (
    <>
      <Header onLogout={onLogout}/>
      <div>관리관리관리</div>
      <Bottom />
    </>
  );
};
export default EmpPage;
