package polymorphism_3_1_2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		
		/* 결합도를 낮추는 방법 - 
		 *    1. 인터페이스를 이용하는 방법
		 *    2. 디자인 패턴중 Factory 패턴을 적용하는 방법
		 *        - 객체를 생성하는 클래스 (BeanFactory) <== 객체를 관리
		 *    3. Spring 의 bean 컨테이너를 이용하는 방법 
		 *    		IoC (제어의 역행) : 
		 *    			- 개발자가 객체를 관리하는 것이 아니라 스프링에게 위임 
		 *    		DI (객체를 코드내에서 생성하지 않고 외부 컨테이너에서 생성해서 의존성 주입     
		 */
	
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		/*스프링 컨테이너로 부터 필요한 객체를 요청한다. */ 
		TV tv =(TV) factory.getBean("tv");
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	
	}

}
