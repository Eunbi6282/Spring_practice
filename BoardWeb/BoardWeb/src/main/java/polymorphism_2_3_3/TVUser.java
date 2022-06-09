package polymorphism_2_3_3;

public class TVUser {

	public static void main(String[] args) {
		// TVUSer클래스에서 객체를 생성하지 않고 외부 클래스에서 객체를 생성 후 생성된 객체를 가지고 옴
			// 결합도를 낮추는 방법, 유지 보수를 아주 쉽게 해준다.
		
		// 결합도를 낮추는 2가지 방법 
			// 1. 인터페이스를 사용 (동일한 메서드)
			// 2. 디자인 패턴 Factory Pattern
				// 객체를 생성하는 별도의 클래스를 둔다.
		
		BeanFactory factory = new BeanFactory();
		
		TV tv = (TV)factory.getBean(args[0]);  // 가져온 객체를 TV타입으로 업캐스팅 
		
		tv.powerOn(); // tv의 메서드 호출 ==> 생성된 객체의 메서드를 호출
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
	}

}
