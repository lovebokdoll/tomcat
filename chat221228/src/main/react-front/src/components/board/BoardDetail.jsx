import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { boardListDB } from "../../service/dbLogic";
import { ContainerDiv, FormDiv, HeaderDiv } from "../styles/FormStyle";
import BoardFileDetail from "./BoardFileDetail";
import BoardHeader from "./BoardHeader";

const BoardDetail = () => {
  const { bm_no } = useParams();
  console.log(bm_no);
  const [files, setFiles] = useState([]); //1.파일명 -bs_file,2.파일크기 -bs_size
  const [pboard, setPBoard] = useState({
    bm_no: bm_no,
  });
  const [board, setBoard] = useState({
    BM_NO: 0,
    BM_TITLE: "",
    BM_WRITER: "",
    BM_CONTENT: "",
    BM_PW: "",
    BM_REG: "",
    BM_GROUP: 0,
    BM_POS: 0,
    BM_STEP: 0,
    BM_HIT: 0,
    BS_FILE: "",
    BS_SIZE: "",
  });
  useEffect(() => {
    const boardDetail = async () => {
      const res = await boardListDB(pboard);
      console.log(res);
      const result = JSON.stringify(res.data);
      const jsonDoc = JSON.parse(result);
      setBoard({
        BM_NO: jsonDoc[0].BM_NO,
        BM_TITLE: jsonDoc[0].BM_TITLE,
        BM_WRITER: jsonDoc[0].BM_PW,
        BM_CONTENT: jsonDoc[0].BM_CONTENT,
        BM_PW: jsonDoc[0].BM_PW,
        BM_REG: jsonDoc[0].BM_REG,
        BM_HIT: jsonDoc[0].BM_HIT,
        BM_GROUP: jsonDoc[0].BM_GROUP,
        BM_POS: jsonDoc[0].BM_POS,
        BM_STEP: jsonDoc[0].BM_STEP,
      });
      //첨부파일,quill editor 이미지파일
      setFiles([jsonDoc[0].BS_FILE, jsonDoc[0].BS_SIZE]);
    };
    boardDetail();
  }, [pboard]);
  /**
   * 의존성 배열에는 제목에 해당되는 bm_no가 변경될 때마다 새로 실행됨
   * 의존성 배열을 적지 않으면 변경될 때마가 새로 읽는다- 초기화된다- 유지안된다
   * 빈배열이면 최초 APP(BoardDetail)이 렌더링 될 때 딱 한번만 실행됨
   * 상태가 바뀔때 3가지
   * 1.state
   * 2.props
   * 3.부모
   * 주의사항 : boards와 같은 n개 row를 갖는 변수명을 사용하면 안된다. => 무한루프(리렌더링)
   */
  return (
    <>
      <ContainerDiv>
        <HeaderDiv>
          <h3 style={{ marginLeft: "10px" }}>계층형 게시판</h3>
        </HeaderDiv>
        <FormDiv>
          <BoardHeader board={board} bm_no={bm_no} />
          <section style={{ minHeight: "400px" }}>
            {/*오라클 서버에서 읽어온 bm_content정보는 quill editor에 출력하는 코드임 */}
            <div dangerouslySetInnerHTML={{ __html: board.BM_CONTENT }}></div>
          </section>
          <BoardFileDetail files={files} />
          <hr style={{ height: "2px" }} />
        </FormDiv>
      </ContainerDiv>
    </>
  );
};

export default BoardDetail;
