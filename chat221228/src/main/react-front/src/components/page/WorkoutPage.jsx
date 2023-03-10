import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Bottom from "../include/Bottom";
import Header from "../include/Header";
import Workouts from "../workout/Workouts";
const WorkoutPage = ({
  authLogic,
  workouts,
  onIncrement,
  onDecrement,
  onDelete,
  onAdd
}) => {
  // 굳이 workouts 인 이유는
  //const WorkoutPage((props))
  //const { authLogic, workouts, onIncrement } = props ===> 구조분해할당
  const navigate = useNavigate();
  const onLogout = () => {
    console.log("HomePage onLogout 호출");
    authLogic.logout();
  };
  const handleIncrement = (item) => {
    //자바스크립트는 같은 이름의 함수를 여러개 정의불가

    //상위 컴포넌트(App.jsx)의 함수를 호출하는 코드임
    //WorkoutPage의 App에서 선언된 workouts로 주소번지를 가지고 있는데
    //왜 여기서는 처리 못하고 다시 상위컴포넌트로 미루는 걸까?
    // setItems 훅을 사용할 수 없기 때문이다.
    //setItems는 파라미터로 넘기지 않는다 왜냐면 해당컴포넌트의 화면 렌터링과 관련된 함수라서.
    onIncrement(item);
  };
  const handleDecrement = (item) => {
    onDecrement(item);
  };
  const handleDelete = (item) => {
    onDelete(item);
  };
  const handleAdd = (name) => {
    //부모에 정의된 함수 호출하기 - 파라미터 값은 AddForm에서 inputRef로 설정된 값 가져옴
    onAdd(name);
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
      <Workouts
        workouts={workouts}
        onIncrement={handleIncrement}
        onDecrement={handleDecrement}
        onDelete={handleDelete}
        onAdd={handleAdd}
      />
      <Bottom />
    </>
  );
};

export default WorkoutPage;
