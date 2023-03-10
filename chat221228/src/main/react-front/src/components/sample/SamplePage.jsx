import React from "react";
import SampleBottom from "./SampleBottom";
import SampleHeader from "./SampleHeader";
import SubPage from "./SubPage";

const SamplePage = (props) => {
  const handleAdd = (num) => {
    console.log("SamplePage handleAdd : " + num);
    props.handleAdd(num);
  };
  return (
    <>
      <SampleHeader num={props.num} />
      <div
        style={{ border: "5px solid gray", width: "600px", height: "400px" }}
      >
        메인페이지영역
        <SubPage handleAdd={handleAdd} />
      </div>
      <SampleBottom />
    </>
  );
};

export default SamplePage;
