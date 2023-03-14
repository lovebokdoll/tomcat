package com.pojo.step3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HandlerMapping {
    /*************************************************
     * @param upmu[](upmu[0]-업무명[0],upmu[1]-메소드명,기능명,페이지이름,분기])
     * @param request                                           1-1,1-2와는 다르게 인터페이스를 implements를 하지 않는다.
     * @param response
     *                                                          질문:어디서 받아오지?
     * @return Object
     * 
     * 
     * 
     * 
     *************************************************/
    public static Object getController( String[] upmu, HttpServletRequest req, HttpServletResponse res )
                    throws ServletException, IOException {
        log.info( "upmu[0]:" + "," + upmu[0] + "," + "upmu[1]:" + upmu[1] );
        Controller3  controller3  = null;
        String       path         = null;
        Object       obj          = null;
        ModelAndView modelAndView = null;
        
        if ( "common".equals( upmu[0] ) ) {
            controller3 = new CommonController();
            
            if ( "zipcodeList".equals( upmu[1] ) ) { // html화면으로 출력 -text/html
                obj = controller3.zipcodeList( req, res );
                
                // 리턴타입이 ModelAndView인 경우
                if ( obj instanceof ModelAndView ) {
                    return ( ModelAndView ) obj;
                }
                // 리턴타입이 String인 경우
                else if ( obj instanceof String ) {
                    return ( String ) obj;
                }
            } ///end of zipcodeList
        }
        else if ( "board3".equals( upmu[0] ) ) {
            controller3 = new Board3Controller();
            
            // 게시글 전체목록
            if ( "boardList".equals( upmu[1] ) ) { // html화면으로 출력 -text/html
                obj = controller3.boardList( req, res );
                
                // 리턴타입이 ModelAndView인 경우
                if ( obj instanceof ModelAndView ) {
                    return ( ModelAndView ) obj;
                }
                // 리턴타입이 String인 경우
                else if ( obj instanceof String ) {
                    return ( String ) obj;
                }
            }
            else if ( "jsonBoardList".equals( upmu[1] ) ) { // json포맷으로 나감 - application/json
                obj = controller3.jsonBoardList( req, res );
                
                // 리턴타입이 ModelAndView인 경우
                if ( obj instanceof ModelAndView ) {
                    return ( ModelAndView ) obj;
                }
                // 리턴타입이 String인 경우
                else if ( obj instanceof String ) {
                    return ( String ) obj;
                }
            }
            else if ( "boardDetail".equals( upmu[1] ) ) {
                obj = controller3.boardDetail( req, res );
                
                // 리턴타입이 ModelAndView인 경우
                if ( obj instanceof ModelAndView ) {
                    return ( ModelAndView ) obj;
                }
                // 리턴타입이 String인 경우
                else if ( obj instanceof String ) {
                    return ( String ) obj;
                }
            }
            else if ( "boardInsert".equals( upmu[1] ) ) { // 글입력 - 새글쓰기와 댓글쓰기
                obj = controller3.boardInsert( req, res );
                log.info( "boardInsert호출 => boolean" + obj instanceof String );
                
            }
            else if ( "imageUpload".equals( upmu[1] ) ) { // 리액트 quill editor 이미지 추가
                obj = controller3.imageUpload( req, res );
                log.info( "imageUpload호출 => boolean" + obj instanceof String );
                
            }
            else if ( "imageDownload".equals( upmu[1] ) ) { // 리액트 quill editor 이미지 추가
                obj = controller3.imageDownload( req, res );
                log.info( "imageDownload호출 => boolean" + obj instanceof String );
                
            }
            else if ( "imageGet".equals( upmu[1] ) ) { // 리액트 quill editor 이미지 추가
                obj = controller3.imageGet( req, res );
                log.info( "imageGet호출 => boolean" + obj instanceof String );
                
            }
            else if ( "boardUpdate".equals( upmu[1] ) ) { // 글수정 - 첨부파일 수정 유무 고려하기
                obj = controller3.boardUpdate( req, res );
                
            }
            else if ( "boardDelete".equals( upmu[1] ) ) { // 글입력 - 첨부파일 삭제유무 고려하기
                obj = controller3.boardDelete( req, res );
                
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
