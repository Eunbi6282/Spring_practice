package polymorphism_4_2;

public class SonySpeaker {
	
	// 기본 생성자 
	public SonySpeaker() {
		System.out.println("--> 소니스피커 객체 생성");
	}
	
	public void volumUp() {
		System.out.println("소니스피커 -- 소리를 올린다.");
	}

	public void volumDown() {
		System.out.println("소니스피커 -- 소리를 줄인다.");
	}
}
