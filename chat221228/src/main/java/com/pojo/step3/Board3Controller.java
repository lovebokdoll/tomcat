package com.pojo.step3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.HashMapBinder;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board3Controller implements Controller3 {
    Board3Logic board3Logic = new Board3Logic();
    String      path;
    
    @Override
    public ModelAndView boardList( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardList호출" );
        List<Map<String, Object>> boardList = null;
        
        // 사용자가 조건검색을 원하는 경우 - 조건 값을 전달할 객체 생성함
        // Mybatis에서는 동적쿼리를 지원하므로 하나의 쿼리문을 가지고 2가지경우 사용가능함
        Map<String, Object> pMap = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind( pMap );
        
        boardList = board3Logic.boardList( pMap );
        
        ModelAndView mav = new ModelAndView( req );
        log.info( boardList );
        mav.setViewName( "board3/boardList" );
        mav.addObject( "boardList", boardList );
        return mav;
    }
    
    @Override
    public Object jsonBoardList( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "jsonBoardList호출" );
        List<Map<String, Object>> bList = null;
        Map<String, Object>       pMap  = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind(pMap);
        bList = board3Logic.boardList( pMap );
        //오라클 연동후에 조회결과가 bList에 담겨있음
        //forward할 때 그 주소번지를 저장해둠 - 화면(jsonBoardList.jsp)에서 접근 가능하다 - 키값이 중요
        req.setAttribute( "bList", bList );
        return "forward:board3/jsonBoardList";
    }
    
    @Override
    public Object boardDetail( HttpServletRequest req, HttpServletResponse res ) {
        log.info( "boardDetail호출" );
        List<Map<String, Object>> bList = null;
        // 전체 조회에 대한 sql문 재사용가능함 - 1건조회
        Map<String, Object> pMap = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind( pMap );
        bList = board3Logic.boardDetail( pMap );
        log.info( "bList" );
        req.setAttribute( "bList", bList );
        return "forward:board3/boardDetail";
    }
    /*
     * INSERT INTO
     * board_master_t(bm_no,bm_title,bm_writer,bm_content,bm_reg,bm_hit)
     * ,bm_group,bm_pos,bm_step)
     * VALUES(seq_board_no.nextval,#{bm_title},#{bm_writer},#{bm_content},to_char(sysdate,'YYYY-MM-DD')
     * ,0,#{bm_group},#{bm_pos},#{bm_step})
     * 
     * 화면에서 받아올 값 - bm_title,bm_writer,bm_content
     * 그렇지 않은 경우 - bm_group,bm_pos,bm_step,bm_reg
     */
    
    @Override
    public Object boardInsert( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "boardInsert호출" );
        int result = 0;
        // 폼태그 안에 사용자가 입력한 정보(bm_writer,bm_title...)를 받아온다.
        // req.getPatemeter("bm_writer")
        // req.getPatemeter("bm_title")
        // req.getPatemeter("bm_content")
        Map<String, Object> pMap = new HashMap<>();
        log.info( "before :" +pMap );
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.multiBind( pMap );
        log.info( "after :" +pMap );
       // result = board3Logic.boardInsert( pMap );
        log.info( result );
        
        if ( result == 1 ) {
            path = "redirect:/board3/boardList.st3";
        }
        else {
            path = "boardInsertFail.jsp";
            res.sendRedirect( path );
        }
        return path;
    }
    
    @Override
    public Object boardUpdate( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        log.info( "boardUpdate호출" );
        Map<String, Object> pMap = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind( pMap );
        log.info( pMap );
        // result(0인데->1로 바꾸어주는)값의 변화를 주는 코드추가
        int result = board3Logic.boardMUpdate( pMap );
        
        if ( result == 1 ) {
            path = "redirect:/board3/boardList.st3";
        }
        else {
            path = "boardInsertFail.jsp";
            res.sendRedirect( path );
        }
        return path;
    }
    
    @Override
    public Object boardDelete( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        Map<String, Object> pMap = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind( pMap );
        log.info( pMap );
        int result = board3Logic.boardMDelete( pMap );
        
        if ( result == 1 ) {
            path = "redirect:/board3/boardList.st3";
        }
        else {//result=0인경우 else타게 되므로 
            path = "redirect:/board/board3/InsertFail.jsp";
            res.sendRedirect( path );
        }
        return path;
    }
    //Quill editor에서 이미지 선택하면 업로드 처리 - 물리적인 위치 - 톰캣서버 - chat221228 - webapp - pds
    //첨부파일 업로드 API는 cos.jar사용 - maven repo
    @Override
    public Object imageUpload(HttpServletRequest req, HttpServletResponse res) {
        log.info("imageUpload 호출 성공");
        //첨부파일 처리에 필요한 변수 선언
        //get 방식 - header에 담김 -> 쿼리스트링
        //post - encType속성 -request.getParameter("") 사용자가 입력한 값을 읽을 수 없음
        MultipartRequest multi = null;//post이면서 첨부파일이 있는 형태인 경우 이 클래스가 반드시 필요하다.
        String realFolder = "D:\\tomcat\\chat221228\\src\\main\\webapp\\pds";
        //첨부파일의 한글처리
        String encType = "utf-8";
        //첨부파일의 크기
        int maxSize = 50*1024*1024;//5MB
        try {
            //인스턴스화 하기 - 인스턴스화 성공하자 마자 pds폴더에 추가됨 
            //@param1 -req요청 -body에 담김-단위테스트 불가함
            //@param2 -실제 파일이 있는 물리적인 위치
            //@param3 -최대크기값지정
            //@param4 -한글 인코딩 설정값
            //@param5 -옵저버 -같은 이름이 있을여우 관찰하고 거기에 대한값을 대응한다. 반환한다.
            multi = new MultipartRequest(req, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
        } catch (Exception e) {
            log.info("Exception : "+e.toString());
        }
        //String filename = boardLogic.imageUpload(multi, realFolder);
        Map<String,Object> rMap = board3Logic.imageUpload(multi, realFolder);
        log.info(rMap);
        //Gson g = new Gson();
        //String temp = g.toJson(rMap);
        //logger.info(temp);
        //logger.info(g);
        String temp = "";
        temp = rMap.get("bs_file").toString()+","+rMap.get("bs_size").toString();
        log.info(temp);
        return temp;
    }
    @Override
    public Object imageGet(HttpServletRequest req, HttpServletResponse res) {
        String b_file = req.getParameter("imageName");
        log.info("imageGet 호출 성공===>"+b_file);
        String filePath = "D:\\tomcat\\chat221228\\src\\main\\webapp\\pds"; // 절대경로. 
        String fname = b_file;
        log.info("b_file: 8->euc"+b_file);       
        File file = new File(filePath,b_file.trim());
        String mimeType = req.getServletContext().getMimeType(file.toString());
        if(mimeType == null){
            //마임타입이 null이면 아래 속성값으로 마임타입을 설정해줌
            //왜 이렇게 하나요?? 브라이주너느 해석이 가능한 마입타입은
            //강제로 다운로드 처리를 위한 속성값 변경
            res.setContentType("application/octet-stream");
        }
        String downName = null;
        FileInputStream fis = null;
        ServletOutputStream sos = null;
        try{
            if(req.getHeader("user-agent").indexOf("MSIE")==-1){
                downName = new String(b_file.getBytes("UTF-8"),"8859_1");
            }else{
                downName = new String(b_file.getBytes("EUC-KR"),"8859_1");
            }
            res.setHeader("Content-Disposition", "attachment;filename="+downName);
            //위에서 생성된 파일 문자열 객체를 가지고 파일생성에 필요한 객체의 파라미터로 넘김
            fis = new FileInputStream(file);
            sos = res.getOutputStream();
            // 파일내용을 담을 바이트 배열을 생성
            byte b[] = new byte[1024*10];
            int data = 0;
            while((data=(fis.read(b,0,b.length)))!=-1){
                //파일에서 읽은 내용을 가지고 실제 파일에 쓰기처리함
                sos.write(b,0,data);
            }
            //처리한 내용이 버퍼에 있는데 이것을 모두 처리요청을 하기
            //내보내고 버퍼를 비운다..-버퍼는 크기가 작음 -> 휘발성
            sos.flush();        
        }catch(Exception e){
            log.info(e.toString());
        }finally{
            try {
                if(sos != null) sos.close();
                if(fis != null) fis.close();                
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        
        //byte[] fileArray = boardLogic.imageDownload(imageName);
        //logger.info(fileArray.length);
        return null;
    }// end of imageGet
    
    public void imageDownload(HttpServletRequest req, HttpServletResponse res) {
        log.info("imageDownload 호출 성공");
        String b_file = req.getParameter("imageName");
        String filePath = "D:\\tomcat\\chat221228\\src\\main\\webapp\\pds"; // 절대경로. 
        String fname = b_file;
        log.info("b_file: 8->euc"+b_file);       
        File file = new File(filePath,b_file.trim());
        String mimeType = req.getServletContext().getMimeType(file.toString());
        if(mimeType == null){
            res.setContentType("application/octet-stream");
        }
        String downName = null;
        FileInputStream fis = null;
        ServletOutputStream sos = null;
        try{
            if(req.getHeader("user-agent").indexOf("MSIE")==-1){
                downName = new String(b_file.getBytes("UTF-8"),"8859_1");
            }else{
                downName = new String(b_file.getBytes("EUC-KR"),"8859_1");
            }
            res.setHeader("Content-Disposition", "attachment;filename="+downName);
            fis = new FileInputStream(file);
            sos = res.getOutputStream();
            byte b[] = new byte[1024*10];
            int data = 0;
            while((data=(fis.read(b,0,b.length)))!=-1){
                sos.write(b,0,data);
            }
            sos.flush();        
        }catch(Exception e){
            log.info(e.toString());
        }finally{
            try {
                if(sos != null) sos.close();
                if(fis != null) fis.close();                
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
    }// end of imageDownload
}
