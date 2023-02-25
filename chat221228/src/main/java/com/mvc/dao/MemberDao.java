package com.mvc.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class MemberDao {
	// log4j모듈을 활용해서 로그 출력 - 날짜 시간 클래스명 라인번호 링크
	// sysout 으로 출력하는것 보다 더 다양한 정보 얻을 수 있음 -> 그래서 객체 생성하는것임
	private static final Logger logger = LogManager.getLogger();
	// MyBatisCommonFactory는 다오 클래스롸 오라클 서버 사이에 MyBatis Layer에 필요한 설정 내용을 담은 공통코드
	// 이 파일에서는 member.xml의 물리적인 위치와 오라클서버의 정보가 담긴 MyBatisConfig.xml의 정보를 IO로 읽어오는
	// 코드가 포함됨
	// 컨셉: 마이바티스는 쿼리문을 xml에 따로 관리한다 자바로 관리하는것 보다 컴파일을 하지않아도 되고 버전관리에도 효과적임
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();

	public Map<String, Object> login(Map<String, Object> pMap) {
		Map<String, Object> rmap = null;
		// MybatisConfig.xml문서를 통해 스캔한 오라클 서버 정보로 연결통로 확보
		SqlSessionFactory sqlSessionFactory = null;
		// 위에서 sqlSessionFactory생성되면 쿼리문 요청히는 selectOne메소드가 필효낳다
		// 그 메소드를 제공하는 클래스 및 commit,rollback지원
		SqlSession sqlSession = null;
		try {
			// 공통 코드에서 연결통로 확보
			sqlSessionFactory = mcf.getSqlSessionFactory();
			// 연결통로 확보로 생성된 객체로 sqlSession로딩하기
			sqlSession = sqlSessionFactory.openSession();
//			pMap=new HashMap<>();
//			pMap.put("mem_id","tomato");
//			pMap.put("mem_pw","123");
			rmap = sqlSession.selectOne("login", pMap);
			logger.info(rmap);// 3건 조회;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rmap;
	}

	public static void main(String args[]) {
		MemberDao mDao = new MemberDao();
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("mem_id", "tomato");
		pMap.put("mem_pw", "123");
		Map<String, Object> rMap = mDao.login(pMap);
		System.out.print(rMap);
	}
}
