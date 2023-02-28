import React from "react";
import Header from "../include/Header";
import Bottom from "../include/Bottom";
import { useParams } from "react-router-dom";
//로그아웃처리를 위해서 props에 authLogic에 주소번지를 받아온다

const HomePage = ({ authLogic }) => {
  let { userId } = useParams();
  console.log(userId);
  const onLogout = () => {
    authLogic.logout();
  };
  return (
    <React.Fragment>
      <Header userId={userId} onLogout={onLogout} />
      <div>HomePage페이지</div>
      <Bottom />
    </React.Fragment>
  );
};
export default HomePage;
