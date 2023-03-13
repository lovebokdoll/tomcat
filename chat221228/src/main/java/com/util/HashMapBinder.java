package com.util;

import java.io.File;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import lombok.extern.log4j.Log4j2;

// Spring부트에서는 RequestParam이 대신 해줌,Model,ModelMap
// 사용자가 입력한 값을 Map에 담아 준다.
// 담을 map은 컨트롤계층에서 bind메소드 호출시 파라미터를 이용해서 원본주소번지를 받아온다.
// 그리고 그 안에 담는다.
// Model,ModelMap
@Log4j2
public class HashMapBinder {
    
    // 표준요청객체
    HttpServletRequest req; // 전역변수
    
    // cos.jar에서 제공하는 요청객체임 - 첨부파일로 post이면 encType속성이 적용된 경우 사용할것
    MultipartRequest multi;
    
    // 첨부파일의 업로드 물리적인 경로
    String realFolder;
    
    // 첨부파일의 한글처리
    String encType = "UTF-8";
    
    // 첨부파일 최대크기
    int maxSize = 5 * 1024 * 1024;
    
    public HashMapBinder() {}
    
    // 생성자 파라미터에 요청객체(지역변수)가 필요한 이유는??
    public HashMapBinder( HttpServletRequest req ) {
        // 생성자의 1역할 - 전역변수 초기화
        this.req = req;
        realFolder = "D:\\tomcat\\chat221228\\src\\main\\webapp\\pds";
    }
    
    public void multiBind( Map<String, Object> pMap ) {
        log.info( "multiBind호출" );
        // 컨트롤 계층에서 생성한 맵 객체 비우기
        pMap.clear();
        
        try {
            multi = new MultipartRequest( req, realFolder, maxSize, encType, new DefaultFileRenamePolicy() );
        }
        catch ( Exception e ) {
            log.error( "{}", e );
        }
        Enumeration<String> en = multi.getParameterNames();
        
        while ( en.hasMoreElements() ) {
            String key = en.nextElement();
            pMap.put( key, multi.getParameter( key ) );
        }
        // 첨부파일과 관련된 정보를 받아오기
        Enumeration<String> files = multi.getFileNames();// n개만큼 처리가 가능
        
        if ( files != null ) {
            // 업로드의 대상파일을 객체로 만듦
            File file = null; // 내용까지 복제되는 건 아니다. 파일명만 객체화 해준다
            
            while ( files.hasMoreElements() ) {
                String fname = files.nextElement();
                multi.getFilesystemName( fname );
                String filename = multi.getFilesystemName( fname );
                pMap.put( "bs_file", filename );
                
                if ( filename != null && filename.length() > 1 ) {
                    file = new File( realFolder + "\\" + filename );
                }
                log.info( file );
            } // end of while
              // 첨부파일의 크기를 담을 수 있는 변수 선언
            double size = 0;
            
            if ( file != null ) {
                size = file.length();// 파일 크기를 byte단위로 담음
                size = size / 1024.0;// byte->kbyte
                pMap.put( "bs_size", size );
            }
        }// end of if
    }
    /*
     * 어떤 페이지 어떤 상황에서 공통코드 재사용 가능하게 할것인가?
     * 업무별 요청 클래스에서 주입받을 객체를 정해서 메소드의 파라미터로
     * 전달받음, 전달받은 주소번지 원본에 값을 담아준다
     * 반환타입이 void인데 파라미터로 넘어간 pMap을 어떻게 쓸 수 있을까??
     */
    
    public void bind( Map<String, Object> pMap ) {
        log.info( "bind호출" );
        pMap.clear();
        Enumeration<String> en = req.getParameterNames();
        
        while ( en.hasMoreElements() ) {
            String key = en.nextElement();
            pMap.put( key, req.getParameter( key ) );
        }
    }
}
