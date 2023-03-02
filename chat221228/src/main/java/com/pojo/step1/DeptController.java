package com.pojo.step1;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DeptController implements Action {
    // SELECT deptno, dname, loc FROM dept
    //조회 입력 수정 삭제 ( 메소드 4가지가 필요)
    //Action implements했으니까 서블릿이 아님 => 메소드 나눌 수 없음..
    //Action implements,추상메소드 이름이 execute인 메소드를 override했다 => 그래서 서블릿이 아닌데도 req와 res사용할 수 있음
    // 서블릿이 아니니까 doGet,doPost사용 할 수 없음
    //사용자가 정의한 메소드는 req와 res주입받을 수 없다
    @Override
    public ActionForward execute( HttpServletRequest req, HttpServletResponse res )
                    throws ServletException, IOException {
        ActionForward actionForward = new ActionForward();
        //FrontMVC1클래스에서 request객체에 저장된 배열 꺼내기
        String[]      upmu          = ( String[] ) req.getAttribute( "upmu" );
        DeptLogic     deptLogic     = new DeptLogic();
        String        path          = null; // 페이지 저장됨
        boolean       isRedirect    = true;
        
        // 너 부서목록 조회할거니 ?
        if ( "getDeptList".equals( upmu[1] ) ) {
            List<Map<String, Object>> deptList = deptLogic.getDeptList();
            req.setAttribute( "deptList", deptList );
            //응답페이지 이름을 결정
            path = "getDeptList.jsp";
            //redirect로 할지 forward로 할지 결정함
            isRedirect = false; // false이면 forward이다 - 요청이 유지된다. - 주소창은 그대로인데 페이지는 바뀜
        }
        // 너 부서등록 하려구?
        else if ( "jsonDeptList".equals( upmu[1] ) ) {
            String jsonDeptList = deptLogic.jsonDeptList();
            req.setAttribute( "jsonDeptList", jsonDeptList );
            path = "jsonDeptList.jsp";
            isRedirect = false; // false이면 forward이다 - 요청이 유지된다. - 주소창은 그대로인데 페이지는 바뀜
        }
        else if ( "deptInsert".equals( upmu[1] ) ) {
            // INSERT INTO SCOTT.DEPT (DEPTNO,DNAME,LOC) VALUES(?,?,?)
            int result = deptLogic.deptInsert();
        }
        // 너 부서정보 수정해야돼 ?
        else if ( "deptUpdate".equals( upmu[1] ) ) {
            int result = deptLogic.deptUpdate();
        }
        // 너희 부서 없어졌다.
        else if ( "deptDelete".equals( upmu[1] ) ) {
            int result = deptLogic.deptDelete();
        }
        actionForward.setPath( path );
        actionForward.setRedirect( isRedirect );
     //  logger.info( "path = " + path + ", isRedirect = " + isRedirect );
        return actionForward;
    }
    
    public ActionForward getDeptList() {
        // res가 없으면 res.sendRedirect(); 불가능
        
        return null;
    }
    
}