import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Bottom from "../include/Bottom";
import Header from "../include/Header";

const BoardPage = ({ authLogic }) => {
  //Single Page Application
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
      <Header onLogout={onLogout} />
      BoardPage
      <Bottom />
    </>
  );
};

export default BoardPage;
