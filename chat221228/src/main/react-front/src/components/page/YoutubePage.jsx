import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Bottom from "../include/Bottom";
import Header from "../include/Header";
const YoutubePage = ({ authLogic }) => {
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
      YoutubePage
      <Bottom />
    </>
  );
};

export default YoutubePage;
