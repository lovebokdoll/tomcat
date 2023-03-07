package clone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.step3.Board3Controller;
import com.pojo.step3.ModelAndView;

public class HandlerMapping7 {
    /*************************************************
     * @param res
     * @param req
     * @param upmu[](upmu[0]-업무명[0],upmu[1]-메소드명,기능명,페이지이름,분기])
     * @param request                                           1-1,1-2와는 다르게 인터페이스를 implements를 하지 않는다.
     * @param response
     *                                                          질문:어디서 받아오지?
     * @return Object
     * 
     *************************************************/
    public static Object getController( String[] upmu, HttpServletRequest req, HttpServletResponse res )
                    throws ServletException, IOException {
        Controller7 controller7 = null;
        Object      obj         = null;
        
        if ( "board3".equals( upmu[0] ) ) {
            controller7 = new BoardController7();
            
            // 게시글 전체목록
            if ( "boardList".equals( upmu[1] ) ) { // html화면으로 출력 -text/html
                obj = controller7.boardList( req, res );
                
            }
            else if ( "jsonBoardList".equals( upmu[1] ) ) { // json포맷으로 나감 - application/json
                obj = controller7.jsonBoardList( req, res );
                
            }
            else if ( "boardDetail".equals( upmu[1] ) ) {
                obj = controller7.boardDetail( req, res );
                
            }
            else if ( "boardInsert".equals( upmu[1] ) ) { // 글입력 - 새글쓰기와 댓글쓰기
                obj = controller7.boardInsert( req, res );
                
            }
            else if ( "boardUpdate".equals( upmu[1] ) ) { // 글수정 - 첨부파일 수정 유무 고려하기
                obj = controller7.boardUpdate( req, res );
                
            }
            else if ( "boardDelete".equals( upmu[1] ) ) { // 글입력 - 첨부파일 삭제유무 고려하기
                obj = controller7.boardDelete( req, res );
            }// end of 게시판구현
            
            // 인증관리 - 이순신
            else if ( "auth".equals( upmu[0] ) ) {
                
            }
            // 회원관리 - 이순신
            else if ( "member".equals( upmu[0] ) ) {
                
            }
            // 주문관리 - 강감찬
            else if ( "order".equals( upmu[0] ) ) {
                
            }
        }
        
        return obj;
    }
}
