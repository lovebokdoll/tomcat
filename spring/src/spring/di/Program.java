package spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.ui.ExamConsole;

public class Program {

	public static void main(String[] args) {
		// 둘 사이의 관계가 부모자식이면 상관이 없음!
		// Exam은 단순한 부모가 아니라 interface로 등록할것임 그리고 NewlecExam 은 데이터 구현객체가 될것임!
		/* 스프링에세 지시하는 방법으로 코드를 변경 
		Exam exam = new NewlecExam();
		ExamConsole console = new InlineExamConsole(exam);
		// 데이터를 받아와야함 - 생성자 톰캣 
		//InlineExamConsole 이 exam를 직접 조립하고 있다.
		//조립되는 곳에 따라서 출력되는 방식이 다르다.
        console.setExam(exam);*/
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("spring/di/setting.xml");
		
		//ExamConsole console = (ExamConsole) context.getBean("console");
		ExamConsole console = context.getBean(ExamConsole.class);
        console.print();
        
	}
}