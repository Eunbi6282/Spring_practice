package polymorphism_4_3_2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class TVUser {

	public static void main(String[] args) {
		// TVUSer클래스에서 객체를 생성하지 않고 외부 클래스에서 객체를 생성 후 생성된 객체를 가지고 옴
			// 결합도를 낮추는 방법, 유지 보수를 아주 쉽게 해준다.
		
		// 결합도를 낮추는 2가지 방법 
			// 1. 인터페이스를 사용 (동일한 메서드)
			// 2. 디자인 패턴 Factory Pattern
				// 객체를 생성하는 별도의 클래스를 둔다.
			// 3. 스프링 프레임워크에서 객체를 생성 후 주입하는 방법
				// IOC(제어의 역행) : 객체 (개발자) ==> 객체(SpringFrameWork)
				// DI(의존성 주입) : 객체를 자바코드 내부에서 생성하지 않고 Spring FrameWork에서 객체를 생성 후 객체를 자바코드 내부로 주입한다.
		
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
				
			// GenericXmlApplicationContext : Bean 설정 파일을 로드
		
		// Spring Container에서 객체를 요청 : 총 4개의 객체 요청청
		TV tv = (TV)factory.getBean("tv");  // 가져온 객체를 TV타입으로 업캐스팅 
		
		tv.powerOn(); // tv의 메서드 호출 ==> 생성된 객체의 메서드를 호출
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
		
		
		
		factory.close();  // 메모리에서 객체 제거
		
		
	
			
		
	}

}
