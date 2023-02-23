package com.pojo.step1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException;
}



//클래스 설계에 인터페이스가 필요하다.
//인터페이스 중심의 코딩을 전개하는 것이 결합도를 낮춰준다-의존성이 낮아짐-> 단위테스트 가넝~~
//따라서 신뢰도 높이는 코드 전개가 가능하다
//HttpServlet에서 강제하는(Override:doGet,doPost) void를 다른 타입으로 바꾸어 보자는 concept
//그래서 아래와 같이 바꾸었지만  파라미터 자리의 req와 res는 개발자가 인스턴스화 하는것이 아니고
//톰캣이 주입해주는 객체임
//의존성 주입이 일어남=> 남이 주입해주는거
// ★이문제를 어떻게 해결하는지를 보는것이 중요함 ★
