import React, { useState } from "react";
import SamplePage from "./components/sample/SamplePage";

const SampleApp = () => {
const[num,setNum]=useState(0);
const handleAdd=()=>{
  setNum(num+1)
}
  return (
  <>
    <SamplePage  num={num} handleAdd={handleAdd} />
  </>
  )
};

export default SampleApp;
