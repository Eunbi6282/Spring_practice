package polymorphism_3_2_4;

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
		// 스프링 컨테이너로 부터 총 4개의 객체를 요청 한다. 
		TV tv11 =(TV) factory.getBean("tv");
		TV tv12 =(TV) factory.getBean("tv");
		TV tv13 =(TV) factory.getBean("tv");
		
		factory.close();
		
		
		/* 별개의 객체들 : 메모리의 낭비를 초래....*/
		/*
		TV tv1 = new SamsungTV();
		TV tv2 = new SamsungTV();
		TV tv3 = new SamsungTV();
		*/
		/* 객체의 주소를 복사해서 생성하면 메모리의 낭비를 줄일수 있다. */
		/*
		TV tv4 = new SamsungTV();
		TV tv5 = tv4;
		TV tv6 = tv5;
		*/
		
		
		
		
		
		
		
	
	}

}
