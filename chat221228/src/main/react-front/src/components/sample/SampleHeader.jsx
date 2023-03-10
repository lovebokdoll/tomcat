import React from "react";

const SampleHeader = (props) => {
  return (
    <>
      <div style={{ border: "3px solid yellowgreen" }}>
        SampleHeader페이지영역
        <br/>
        <h2>{props.num}</h2>
      </div>
    </>
  );
};

export default SampleHeader;
