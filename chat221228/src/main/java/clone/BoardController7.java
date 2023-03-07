package clone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.step3.Board3Logic;
import com.util.HashMapBinder;

public class BoardController7 implements Controller7 {
    BoardLogic7 boardLogic = new BoardLogic7();
    
    @Override
    public ModelAndView7 boardList( HttpServletRequest req, HttpServletResponse res ) {
       
        List<Map<String, Object>> boardList = null;
        Map<String, Object> pMap = new HashMap<>();
        HashMapBinder       hmb  = new HashMapBinder( req );
        hmb.bind( pMap );
        boardList = boardLogic.boardList(pMap);
        
        ModelAndView7 mav = new ModelAndView7(req);
        mav.setViewName("board3/boardList");
        mav.addObject( "boardList", boardList );
        return mav;
    }

    @Override
    public Object boardDetail( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object boardInsert( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object boardUpdate( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object boardDelete( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object jsonBoardList( HttpServletRequest req, HttpServletResponse res ) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
