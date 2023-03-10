import React, { useState } from "react";
/*
const Workout = (props)) => {
const Workout = { workout, onIncrement } = props 
props를 사용하려면 이렇게 두줄을 작성해야 한다 => 구조분해할당
*/
const Workout = ({ workout, onIncrement, onDecrement, onDelete}) => {
  // 미리 구조분해할당으로 받아옴
  console.log(workout);
  const handleIncrement = () => {
    //이벤트 처리가 되어있지 않고 상위컴포넌트 함수를 호출하고 있다
    //상위 컴포넌트의 함수는 props를 통해 접근 가능하다.
    //상위 함수를 호출할 때 파라미터도 넘어 간다.
    onIncrement(workout);
  };
  const handleDecrement = () => {
    onDecrement(workout);
  };
  const handleDelete = () => {
    onDelete(workout);
  };

  return (
    <>
      <li className="habit">
        <span className="habit-name">{workout.name}</span>
        <span className="habit-count">{workout.count}</span>
        <button
          className="habit-button habit-increase"
          onClick={handleIncrement}
        >
          <i className="fas fa-plus-square"></i>
        </button>
        <button
          className="habit-button habit-decrease"
          onClick={handleDecrement}
        >
          <i className="fas fa-minus-square"></i>
        </button>
        <button className="habit-button habit-delete" onClick={handleDelete}>
          <i className="fas fa-trash"></i>
        </button>
      </li>
    </>
  );
};

export default Workout;
