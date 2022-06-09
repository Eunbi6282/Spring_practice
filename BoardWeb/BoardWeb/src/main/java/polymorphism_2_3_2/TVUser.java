package polymorphism_2_3_2;

public class TVUser {

	public static void main(String[] args) {
		// (1) SamSungTV tv = new SamSungTV();
		// (2) LGTV tv = new LGTV();
		
		// (3) TV tv = new SamSungTV(); // 업캐스팅 (다형성 구현)
		TV tv = new LGTV();  
		
		tv.powerOn();
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
	}
	// 인터페이스를 사용하면 약결합을 만들어서 유지보수를 쉽게 한다.
}
