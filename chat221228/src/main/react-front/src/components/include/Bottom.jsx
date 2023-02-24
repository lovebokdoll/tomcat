import { Navbar } from "react-bootstrap";
import React from "react";
const Bottom = () => {
  return (
    <React.Fragment>
      <Navbar
        fixed="bottom"
        className="navbar navbar-expand-sm bg-light justify-content-center"
        bg="dark"
        style={{ color: "white" }}
      >
        자바캠프 Copyright&copy;2023
      </Navbar>
    </React.Fragment>
  );
};

export default Bottom;
