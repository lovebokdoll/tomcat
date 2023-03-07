package clone;

import java.util.List;
import java.util.Map;

public class BoardLogic7 {
    BoardDao7 boardDao = new BoardDao7();
    
    public List<Map<String, Object>> boardList( Map<String, Object> pMap ) {
        List<Map<String, Object>> boardList = boardDao.boardList(pMap);
        return boardList;
    }
}
