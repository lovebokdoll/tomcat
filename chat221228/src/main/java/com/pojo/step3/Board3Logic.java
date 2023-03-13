package com.pojo.step3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oreilly.servlet.MultipartRequest;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Board3Logic {
    Board3Dao board3Dao = new Board3Dao();
    
    public List<Map<String, Object>> boardList( Map<String, Object> pMap ) {
        log.info( "boardList호출" );
        List<Map<String, Object>> boardList = board3Dao.boardList( pMap );
        return boardList;
    }
    
    public List<Map<String, Object>> boardDetail( Map<String, Object> pMap ) {
        log.info( "boardDetail호출" );
        List<Map<String, Object>> boardList = board3Dao.boardList( pMap );
        
        int bm_no = Integer.parseInt( pMap.get( "bm_no").toString() );
        board3Dao.hitCount(bm_no);
        return boardList;
    }
    
    public int boardInsert( Map<String, Object> pMap ) {
        log.info( "boardInsert호출:" + pMap );
        int result   = 0;
        int bm_no    = 0;
        int bm_group = 0;
        bm_no = board3Dao.getBNo();
        pMap.put( "bm_no", bm_no );
        // Map안에서 꺼낸다는 건 화면에서 넘어온 값이라는 뜻
        
        if ( pMap.get( "bm_group" ) != null ) {
            bm_group = Integer.parseInt( pMap.get( "bm_group" ).toString() );
        }
        
        // 댓글쓰기야?
        if ( bm_group > 0 ) {
            board3Dao.bStepUpdate( pMap );
            pMap.put( "bm_pos", Integer.parseInt( pMap.get( "bm_pos" ).toString() ) + 1 );
            pMap.put( "bm_step", Integer.parseInt( pMap.get( "bm_step" ).toString() ) + 1 );
        }
        // 새글쓰기야? -그룹번호 채번 포함
        else {
            bm_group = board3Dao.getBGroup();
            log.info( "새글쓰기 로직 호출=>" + bm_group );
            pMap.put( "bm_group", bm_group );
            pMap.put( "bm_pos", 0 );
            pMap.put( "bm_step", 0 );
        }
        result = board3Dao.boardInsert( pMap );
        //첨부파일이 존재하니?
        if(pMap.get("bs_file")!=null &&pMap.get( "bs_file" ).toString().length()>1) {
            pMap.put("bm_no",bm_no);
            //현재 첨부파일은 하나만 담는 것으로 가정하고 처리함
            pMap.put("bs_seq",1);
            int result2=0;
            result2=board3Dao.boardSInsert( pMap );
            log.info(result2);
        }
        return result;
    }
      public int boardMUpdate( Map<String, Object> pMap ) {
        int result = board3Dao.boardMUpdate( pMap );
        
        return result;
        
    }
    public int boardMDelete( Map<String, Object> pMap ) {
        int result = board3Dao.boardMDelete( pMap );
        return result;
        
    }
    //컨트롤 계층에서 multi주소번지를 넘겨준 이유는
    //실제 파일명과 파일크키를 MAP에 감기
    //반환값을는 - 파일명, 파일크기
    //viewResolver터짐 = pageNove[0] [page[1]번은null인 생태임
    public Map<String, Object> imageUpload(MultipartRequest multi, String realFolder) {
        Map<String, Object> pMap = new HashMap<String, Object>();
        log.info("image:"+multi);
        String filename =  null;
        String fullPath = null;
        //첨부파일에 대한 정보를 받아오는 코드 추가
        Enumeration<String> files = multi.getFileNames();
        log.info("files : "+files);      
        //첨부파일이 존재하나?
        if(files!=null) {
            File file = null;
            while(files.hasMoreElements()) {
                String fname = files.nextElement();
                log.info("fname:"+fname);//bs_file
                filename = multi.getFilesystemName(fname);
                log.info("filename:"+filename);//첨부파일이름
                pMap.put("bs_file", filename);
                //file객체 생성하기 -> 왜냐하면 첨부파일의 크기를 알고 싶어요....
                //file = multi.getFile(filename);
                if(filename !=null && filename.length()>1) {
                    file = new File(realFolder+"\\"+filename);
                }
                log.info("file:"+file);
            }//end of while
            //첨부파일의 크기를 담을 수 있는 변수
            double size = 0;
            if(file != null) {
                size = file.length();//파일 크기가 저장 5.2MB
                log.info("size:"+size);
                size = size/(1024);
                log.info("size/1024:"+size);
                pMap.put("bs_size", size);
            }
            //int result = boardSDao.boardSInsert(pMap);
        }//////end of if        
        //return filename;
        return pMap;
    }
    public byte[] imageDownload(String imageName) {
        String fname = null;
        try {
            fname = URLDecoder.decode(imageName, "UTF-8");
            log.info(fname);
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //out.print("b_file: 8->euc"+b_file);       
        //out.print("<br>");        
        String filePath = "D:\\tomcat\\chat221228\\src\\main\\webapp\\pds"; // 절대경로. 
        //가져온 파일이름을 객체화 시켜줌. - 파일이 있는 물리적인 경로가 필요함.
        File file = new File(filePath, fname.trim());
        
        //해당 파일을 읽어오는 객체 생성해 줌. - 이 때 파일명을 객체화 한 주소번지가 필요함. 
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            fis = new FileInputStream(file);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }
    
}