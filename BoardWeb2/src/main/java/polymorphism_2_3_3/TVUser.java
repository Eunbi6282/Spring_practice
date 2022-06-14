package polymorphism_2_3_3;

public class TVUser {

	public static void main(String[] args) {
		
		/* 결합도를 낮추는 방법 - 
		 *    1. 인터페이스를 이용하는 방법
		 *    2. 디자인 패턴중 Factory 패턴을 적용하는 방법
		 *        - 객체를 생성하는 클래스 (BeanFactory) <== 객체를 관리
		 *         
		 */
	
		BeanFactory factory = new BeanFactory();
		TV tv = (TV) factory.getBean(args[0]);
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

		
	}

}
