import React from "react";
import { Button } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";

const Header = (props) => {
  const { userId, onLogout } = props;
  return (
    <React.Fragment>
      <Navbar bg="light" expand="lg">
        <Container>
          <Navbar.Brand href="#home">Java Camp</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav"/>
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Link to="/" className="nav-link">
                Home
              </Link>
              <Link to="/board" className="nav-link">
                게시판
              </Link>
              <Link to="/workout" className="nav-link">
                홈트
              </Link>
              <Link to="/hackernews" className="nav-link">
                해커뉴스
              </Link>
              <Link to="/youtube" className="nav-link">
                Youtube
              </Link>
            </Nav>
            {onLogout && (
              <Button variant="primary" onClick={onLogout}>
                Logout
              </Button>
            )}
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </React.Fragment>
  );
};

export default Header;
