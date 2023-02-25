package dept.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.Gson;
//데이터에 접근하는 object
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DeptDAO {

	private SqlSessionFactory sqlSessionFactory;

	public List<DeptVO> getDeptList() {
		log.info("getDeptList호출 ");
		sqlSessionFactory = MyBatisSessionFactory.getInstance();// 연결정보를 가져와서 연결대기 
		SqlSession sqlSession = sqlSessionFactory.openSession();// 여기서 데이터 베이스와 연결
		List<DeptVO> deptList = sqlSession.selectList("dept.selectDept");

		deptList.forEach(e -> log.info(e));

		return deptList;
	}

	public int deptInsert(DeptVO deptVO) {
	sqlSessionFactory = MyBatisSessionFactory.getInstance();
	SqlSession sqlSession =sqlSessionFactory.openSession();
	int deptInsert = sqlSession.insert("dept.insertDept", deptVO);
		log.info("deptInsert호출 ");
		return deptInsert;
	}

	public int deptUpdate() {
		log.info("deptUpdate호출 ");
		return 0;
	}

	public int deptDelete() {
		log.info("deptUpdate호출 ");
		return 0;
	}

	public String jsonDeptList() {
		log.info("jsonDeptList호출 ");
		List<Map<String, Object>> deptList = new ArrayList<>();
		Map<String, Object> rmap = new HashMap<>();
		rmap.put("deptno", 10);
		rmap.put("dname", "개발부");
		rmap.put("loc", "부산 ");
		deptList.add(rmap);
		rmap = new HashMap<>();
		rmap.put("deptno", 20);
		rmap.put("dname", "운영부");
		rmap.put("loc", "서울 ");
		deptList.add(rmap);
		rmap = new HashMap<>();
		rmap.put("deptno", 30);
		rmap.put("dname", "총무부");
		rmap.put("loc", "대전 ");
		Gson g = new Gson();
		String temp = g.toJson(deptList);
		return temp;// JSOM포맷으로 전달 -리액트에서 조회시에 사용함
	}
	
	//public static void main(String[] args) {
	//	DeptDAO deptDao = new DeptDAO();
//		DeptVO deptVO = new DeptVO();
//		deptVO.setDeptno(55);
//		deptVO.setDname("다희");
//		deptVO.setLoc("산본 ");
//		log.info(deptVO);
//	int result=	deptDao.deptInsert(deptVO);
//	log.info(result);
	//	deptDao.getDeptList();
		
				
//	}

}
