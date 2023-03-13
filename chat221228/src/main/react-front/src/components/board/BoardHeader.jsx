import React from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const BoardHeader = ({ board }) => {
  const navigate = useNavigate();
  const boardDelete = () => {};
  return (
    <>
      <div>
        <div
          style={{ display: "flex", flexDirection: "column", width: "100%" }}
        >
          <div style={{ display: "flex", justifyContent: "space-between" }}>
            <div style={{ overflow: "auto" }}>
              <span
                style={{
                  marginBottom: "15px",
                  fontSize: "30px",
                  display: "block",
                }}
              >
                {board.BM_TITLE}
              </span>
            </div>
            {
              <div style={{ display: "flex", justifyContent: "flex-end" }}>
                <Button
                  style={{ margin: "0px 10px 0px 10px" }}
                  onClick={() => {
                    navigate(`/board/update?bm_no=${board.BM_NO}`);
                  }}
                >
                  수정
                </Button>
                <Button
                  onClick={() => {
                    boardDelete();
                  }}
                >
                  삭제
                </Button>
              </div>
            }
          </div>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              fontSize: "14px",
            }}
          >
            <div style={{ display: "flex", flexDirection: "column" }}>
              <span>작성자 : {board.BM_WRITER}</span>
              <span>작성일 : {board.BM_REG}</span>
            </div>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                marginRight: "10px",
              }}
            >
              <div style={{ display: "flex" }}>
                <span style={{ marginRight: "5px" }}>조회수 :</span>
                <div
                  style={{
                    display: "flex",
                    justifyContent: "flex-end",
                    width: "30px",
                  }}
                >
                  {board.BM_HIT}
                </div>
              </div>
            </div>
          </div>
        </div>
        <hr style={{ height: "2px" }} />
      </div>
    </>
  );
};

export default BoardHeader;
