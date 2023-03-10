import React, { useState } from "react";

const SubPage = (props) => {
  const handleAdd = () => {
    console.log("SubPage handleAdd호출 ");
    props.handleAdd(props.num);
  };
  const handleMinus = () => {
    console.log("SubPage handleMinus호출");
  };
  return (
    <div style={{ border: "5px solid red", width: "300px", height: "300px" }}>
      서브페이징
      <br />
      <button onClick={handleAdd}>ADD</button>
      <button onClick={handleMinus}>Minus</button>
    </div>
  );
};

export default SubPage;
